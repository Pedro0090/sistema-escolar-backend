package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.component.AlunoDisciplinaComponent;
import com.pedro_augusto.sistema_escolar.component.DisciplinaComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.AlunoMapper;
import com.pedro_augusto.sistema_escolar.mapper.DisciplinaMapper;
import com.pedro_augusto.sistema_escolar.utils.GeradorMatricula;
import com.pedro_augusto.sistema_escolar.utils.TipoMatricula;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AlunoService {

    private final AlunoComponent alunoComponent;
    private final AlunoMapper alunoMapper;
    private final DisciplinaComponent disciplinaComponent;
    private final AlunoDisciplinaComponent alunoDisciplinaComponent;
    private final DisciplinaMapper disciplinaMapper;

    @Autowired
    public AlunoService(AlunoComponent alunoComponent, AlunoMapper alunoMapper, DisciplinaComponent disciplinaComponent, AlunoDisciplinaComponent alunoDisciplinaComponent, DisciplinaMapper disciplinaMapper) {
        this.alunoComponent = alunoComponent;
        this.alunoMapper = alunoMapper;
        this.disciplinaComponent = disciplinaComponent;
        this.alunoDisciplinaComponent = alunoDisciplinaComponent;
        this.disciplinaMapper = disciplinaMapper;
    }

    public List<AlunoListagemDTO> listAll() {
        log.info("Buscando alunos no banco de dados");
        List<AlunoListagemDTO> alunos = alunoMapper.toListAlunoListagemDTO(alunoComponent.findAll());
        log.info("{} alunos encontrados", alunos.size());
        return alunos;
    }

    public List<DisciplinaDTO> listAllDisciplinas(String matricula) {
        AlunoEntity aluno = alunoComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
        List<DisciplinaEntity> disciplinas = new ArrayList<>();
        for (AlunoDisciplinaEntity disciplina : aluno.getDisciplinas()) {
            disciplinas.add(disciplinaComponent.findById(disciplina.getDisciplinaEntity().getId()));
        }
        return disciplinaMapper.toListDisciplinaDTO(disciplinas);
    }

    public AlunoDTO findByMatricula(String matricula) {
        log.info("Buscando aluno com matricula {}", matricula);
        AlunoEntity aluno = alunoComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
        log.info("Aluno com matricula {} encontrado", matricula);
        return alunoMapper.toAlunoDTO(aluno);
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        log.info("Criando aluno {} no banco de dados", alunoDTO.getNome());
        List<DisciplinaEntity> disciplinas = new ArrayList<>();
        if (!alunoDTO.getDisciplinas().isEmpty() && alunoDTO.getDisciplinas() != null) {
            for (Long id : alunoDTO.getDisciplinas()) {
                DisciplinaEntity disciplina = disciplinaComponent.findById(id);
                disciplinas.add(disciplina);
            }
        }
        AlunoEntity alunoEntity = alunoMapper.toAluno(alunoDTO);
        AlunoEntity alunoEntitySalvo =  alunoComponent.adicionarMatriculaAndSalvar(alunoEntity, gerarMatriculaValidaAluno());
        for (DisciplinaEntity disciplina : disciplinas) {
            alunoDisciplinaComponent.setarAndSalvar(alunoEntitySalvo, disciplina);
        }
        log.info("Aluno {} salvo no banco de dados", alunoEntitySalvo.getNome());
        return alunoMapper.toAlunoDTO(alunoEntitySalvo);
    }

    public AlunoDTO replace(AlunoDTO alunoDTO) {
        log.info("Buscando aluno com matricula {} no banco de dados", alunoDTO.getMatricula());
        alunoComponent.findByMatricula(alunoDTO.getMatricula());
        List<DisciplinaEntity> disciplinas = new ArrayList<>();
        if (alunoDTO.getDisciplinas() != null && !alunoDTO.getDisciplinas().isEmpty()) {
            for (Long id : alunoDTO.getDisciplinas()) {
                DisciplinaEntity disciplina = disciplinaComponent.findById(id);
                disciplinas.add(disciplina);
            }
        }
        AlunoEntity alunoAtualizado = alunoMapper.toAluno(alunoDTO);
        for (DisciplinaEntity disciplina : disciplinas) {
            boolean jaExiste = disciplina.getAlunos().stream()
                    .anyMatch(rel -> rel.getAlunoEntity().getId().equals(alunoAtualizado.getId()));

            if (!jaExiste) {
                alunoDisciplinaComponent.setarAndSalvar(alunoAtualizado, disciplina);
            }
        }
        alunoComponent.salvar(alunoAtualizado);
        log.info("Aluno com matricula {} atualizado", alunoAtualizado.getMatricula());
        return alunoMapper.toAlunoDTO(alunoAtualizado);
    }

    public void delete(String matricula) {
        log.info("Deletando usuário com matricula {}", matricula);
        alunoComponent.deletar(matricula);
        log.info("Aluno com matricula {} deletado", matricula);
    }

    public void deleteDisciplina(String matricula, Long id) {
        Optional<AlunoEntity> aluno = alunoComponent.findByMatricula(matricula);
        disciplinaComponent.findById(id);
        AlunoDisciplinaEntity relacionamento = alunoDisciplinaComponent.findByIdAlunoAndIdDisciplina(aluno.get().getId(), id);
        alunoDisciplinaComponent.deletar(relacionamento);
    }

    private String gerarMatriculaValidaAluno() {
        String matricula;
        do {
            matricula = GeradorMatricula.gerarMatricula(TipoMatricula.ALUNO);
        } while (alunoComponent.findByMatricula(matricula).isPresent());
        return matricula;
    }
}