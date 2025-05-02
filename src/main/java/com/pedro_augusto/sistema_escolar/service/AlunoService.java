package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetails;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.AlunoListagemDTOMapper;
import com.pedro_augusto.sistema_escolar.mapper.AlunoPostRequestMapper;
import com.pedro_augusto.sistema_escolar.mapper.AlunoPutRequestAndDetailsMapper;
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
    private final AlunoPutRequestAndDetailsMapper alunoPutRequestAndDetailsMapper;
    private final AlunoPostRequestMapper alunoPostRequestMapper;
    private final AlunoListagemDTOMapper alunoListagemDTOMapper;

    @Autowired
    public AlunoService(AlunoComponent alunoComponent, AlunoPutRequestAndDetailsMapper alunoPutRequestAndDetailsMapper,
                        AlunoPostRequestMapper alunoPostRequestMapper, AlunoListagemDTOMapper alunoListagemDTOMapper) {
        this.alunoComponent = alunoComponent;
        this.alunoPutRequestAndDetailsMapper = alunoPutRequestAndDetailsMapper;
        this.alunoPostRequestMapper = alunoPostRequestMapper;
        this.alunoListagemDTOMapper = alunoListagemDTOMapper;
    }

    public List<AlunoListagemDTO> listAll() {
        log.info("Buscando alunos no banco de dados");
        List<AlunoListagemDTO> alunos = alunoListagemDTOMapper.toListAlunoListagemDTO(alunoComponent.findAll());
        log.info("{} alunos encontrados", alunos.size());
        return alunos;
    }

    public AlunoPutRequestAndDetails findByMatricula(String matricula) {
        log.info("Buscando aluno com matricula {}", matricula);
        AlunoEntity aluno = alunoComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
        log.info("Aluno com matricula {} encontrado", matricula);
        return alunoPutRequestAndDetailsMapper.toAlunoPutRequestAndDetails(aluno);
    }

    public AlunoPostRequestBody save(AlunoPostRequestBody alunoPostRequestBody) {
        log.info("Criando aluno {} no banco de dados", alunoPostRequestBody.getNome());
        AlunoEntity alunoEntity = alunoPostRequestMapper.toAluno(alunoPostRequestBody);
        AlunoEntity alunoEntitySalvo =  alunoComponent.adicionarMatriculaAndSalvar(alunoEntity, gerarMatriculaAluno());
        log.info("Aluno {} salvo no banco de dados", alunoEntitySalvo.getNome());
        return alunoPostRequestMapper.toAlunoPostRequestBody(alunoEntitySalvo);
    }

    public AlunoPutRequestAndDetails replace(AlunoPutRequestAndDetails alunoPutRequestAndDetails) {
        log.info("Buscando aluno com matricula {} no banco de dados", alunoPutRequestAndDetails.getMatricula());
        alunoComponent.findByMatricula(alunoPutRequestAndDetails.getMatricula());
        AlunoEntity alunoAtualizado = alunoComponent.salvar(
                alunoPutRequestAndDetailsMapper.toAluno(alunoPutRequestAndDetails));
        log.info("Aluno com matricula {} atualizado", alunoAtualizado.getMatricula());
        return alunoPutRequestAndDetailsMapper.toAlunoPutRequestAndDetails(alunoAtualizado);
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