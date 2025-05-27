package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.AlunoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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

    public AlunoPutRequestAndDetailsDTO findByMatricula(String matricula) {
        log.info("Buscando aluno com matricula {}", matricula);
        AlunoEntity aluno = alunoComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
        log.info("Aluno com matricula {} encontrado", matricula);
        return alunoMapper.toAlunoPutRequestAndDetailsDTO(aluno);
    }

    public AlunoPostRequestDTO save(AlunoPostRequestDTO alunoPostRequestDTO) {
        log.info("Criando aluno {} no banco de dados", alunoPostRequestDTO.getNome());
        AlunoEntity alunoEntity = alunoMapper.toAluno(alunoPostRequestDTO);
        AlunoEntity alunoEntitySalvo =  alunoComponent.adicionarMatriculaAndSalvar(alunoEntity, gerarMatriculaAluno());
        log.info("Aluno {} salvo no banco de dados", alunoEntitySalvo.getNome());
        return alunoMapper.toAlunoPostRequestDTO(alunoEntitySalvo);
    }

    public AlunoPutRequestAndDetailsDTO replace(AlunoPutRequestAndDetailsDTO alunoPutRequestAndDetailsDTO) {
        log.info("Buscando aluno com matricula {} no banco de dados", alunoPutRequestAndDetailsDTO.getMatricula());
        alunoComponent.findByMatricula(alunoPutRequestAndDetailsDTO.getMatricula());
        AlunoEntity alunoAtualizado = alunoComponent.salvar(
                alunoMapper.toAluno(alunoPutRequestAndDetailsDTO));
        log.info("Aluno com matricula {} atualizado", alunoAtualizado.getMatricula());
        return alunoMapper.toAlunoPutRequestAndDetailsDTO(alunoAtualizado);
    }

    public void delete(String matricula) {
        log.info("Deletando usuário com matricula {}", matricula);
        alunoComponent.deletar(matricula);
        log.info("Aluno com matricula {} deletado", matricula);
    }



    public String gerarMatriculaAluno() {
        StringBuilder matricula;
        do {
            matricula = new StringBuilder("SAA");
            LocalDate data = LocalDate.now();
            matricula.append(data.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            long numero = new Random().nextLong(1, 999999);
            matricula.append(String.format("%06d", numero));
        } while (alunoComponent.findByMatricula(matricula.toString()).isPresent());
        return matricula.toString();
    }
}