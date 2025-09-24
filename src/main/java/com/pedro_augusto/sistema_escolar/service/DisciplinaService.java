package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.component.AlunoDisciplinaComponent;
import com.pedro_augusto.sistema_escolar.component.DisciplinaComponent;
import com.pedro_augusto.sistema_escolar.component.ProfessorComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import com.pedro_augusto.sistema_escolar.mapper.AlunoMapper;
import com.pedro_augusto.sistema_escolar.mapper.DisciplinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaComponent disciplinaComponent;
    private final DisciplinaMapper disciplinaMapper;
    private final ProfessorComponent professorComponent;
    private final AlunoComponent alunoComponent;
    private final AlunoDisciplinaComponent alunoDisciplinaComponent;
    private final AlunoMapper alunoMapper;

    @Autowired
    public DisciplinaService(DisciplinaComponent disciplinaComponent, DisciplinaMapper disciplinaMapper, ProfessorComponent professorComponent, AlunoComponent alunoComponent, AlunoDisciplinaComponent alunoDisciplinaComponent, AlunoMapper alunoMapper) {
        this.disciplinaComponent = disciplinaComponent;
        this.disciplinaMapper = disciplinaMapper;
        this.professorComponent = professorComponent;
        this.alunoComponent = alunoComponent;
        this.alunoDisciplinaComponent = alunoDisciplinaComponent;
        this.alunoMapper = alunoMapper;
    }

    public List<DisciplinaDTO> listAll() {
        return disciplinaMapper.toListDisciplinaDTO(disciplinaComponent.findAll());
    }

    public DisciplinaDTO findById(Long id) {
        DisciplinaEntity disciplina = disciplinaComponent.findById(id);
        return disciplinaMapper.toDisciplinaDTO(disciplina);
    }

    public List<AlunoListagemDTO> listAllAlunos(Long id) {
        DisciplinaEntity disciplina = disciplinaComponent.findById(id);
        List<AlunoEntity> alunos = new ArrayList<>();
        for (AlunoDisciplinaEntity aluno : disciplina.getAlunos()) {
            alunos.add(alunoComponent.findById(aluno.getAlunoEntity().getId()));
        }
        return alunoMapper.toListAlunoListagemDTO(alunos);
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        ProfessorEntity professorSalvo = professorComponent.findById(disciplinaDTO.getProfessorId());
        DisciplinaEntity disciplinaEntity = disciplinaMapper.toDisciplinaEntity(disciplinaDTO, professorSalvo);
        List<AlunoEntity> alunos = new ArrayList<>();
        if (disciplinaDTO.getAlunos() != null && !disciplinaDTO.getAlunos().isEmpty()) {
            for (Long id : disciplinaDTO.getAlunos()) {
                alunos.add(alunoComponent.findById(id));
            }

        }
        DisciplinaEntity disciplinaSalva = disciplinaComponent.salvar(disciplinaEntity);
        for (AlunoEntity aluno : alunos) {
            alunoDisciplinaComponent.setarAndSalvar(aluno, disciplinaSalva);
        }
        return disciplinaMapper.toDisciplinaDTO(disciplinaSalva);
    }

    public DisciplinaDTO replace(DisciplinaDTO disciplinaDTO) {
        disciplinaComponent.findById(disciplinaDTO.getId());
        ProfessorEntity professorSalvo = professorComponent.findById(disciplinaDTO.getProfessorId());
        List<AlunoEntity> alunos = new ArrayList<>();
        if (disciplinaDTO.getAlunos() != null && !disciplinaDTO.getAlunos().isEmpty()) {
            for (Long id : disciplinaDTO.getAlunos()) {
                alunos.add(alunoComponent.findById(id));
            }
        }
        DisciplinaEntity disciplinaAtualizada = disciplinaMapper.toDisciplinaEntity(disciplinaDTO, professorSalvo);
        for (AlunoEntity aluno : alunos) {
            boolean jaExiste = aluno.getDisciplinas().stream()
                    .anyMatch(rel -> rel.getDisciplinaEntity().getId().equals(disciplinaAtualizada.getId()));
            if (!jaExiste) {
                alunoDisciplinaComponent.setarAndSalvar(aluno, disciplinaAtualizada);
            }
        }
        disciplinaComponent.salvar(disciplinaAtualizada);
        return disciplinaMapper.toDisciplinaDTO(disciplinaAtualizada);
    }

    public void delete(Long id) {
       disciplinaComponent.deletar(id);
    }

    public void deleteDisciplina(Long id, String matricula) {
        disciplinaComponent.findById(id);
        Optional<AlunoEntity> aluno = alunoComponent.findByMatricula(matricula);
        AlunoDisciplinaEntity relacionamento = alunoDisciplinaComponent.findByIdAlunoAndIdDisciplina(aluno.get().getId(), id);
        alunoDisciplinaComponent.deletar(relacionamento);
    }
}
