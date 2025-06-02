package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.AlunoMapper;
import com.pedro_augusto.sistema_escolar.utils.GeradorMatricula;
import com.pedro_augusto.sistema_escolar.utils.TipoMatricula;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AlunoService {

    private final AlunoComponent alunoComponent;
    private final AlunoMapper alunoMapper;

    @Autowired
    public AlunoService(AlunoComponent alunoComponent, AlunoMapper alunoMapper) {
        this.alunoComponent = alunoComponent;
        this.alunoMapper = alunoMapper;
    }

    public List<AlunoListagemDTO> listAll() {
        log.info("Buscando alunos no banco de dados");
        List<AlunoListagemDTO> alunos = alunoMapper.toListAlunoListagemDTO(alunoComponent.findAll());
        log.info("{} alunos encontrados", alunos.size());
        return alunos;
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
        AlunoEntity alunoEntity = alunoMapper.toAluno(alunoDTO);
        AlunoEntity alunoEntitySalvo =  alunoComponent.adicionarMatriculaAndSalvar(alunoEntity, gerarMatriculaValidaAluno());
        log.info("Aluno {} salvo no banco de dados", alunoEntitySalvo.getNome());
        return alunoMapper.toAlunoDTO(alunoEntitySalvo);
    }

    public AlunoDTO replace(AlunoDTO alunoDTO) {
        log.info("Buscando aluno com matricula {} no banco de dados", alunoDTO.getMatricula());
        alunoComponent.findByMatricula(alunoDTO.getMatricula());
        AlunoEntity alunoAtualizado = alunoComponent.salvar(
                alunoMapper.toAluno(alunoDTO));
        log.info("Aluno com matricula {} atualizado", alunoAtualizado.getMatricula());
        return alunoMapper.toAlunoDTO(alunoAtualizado);
    }

    public void delete(String matricula) {
        log.info("Deletando usuário com matricula {}", matricula);
        alunoComponent.deletar(matricula);
        log.info("Aluno com matricula {} deletado", matricula);
    }

    private String gerarMatriculaValidaAluno() {
        String matricula;
        do {
            matricula = GeradorMatricula.gerarMatricula(TipoMatricula.ALUNO);
        } while (alunoComponent.findByMatricula(matricula).isPresent());
        return matricula;
    }
}