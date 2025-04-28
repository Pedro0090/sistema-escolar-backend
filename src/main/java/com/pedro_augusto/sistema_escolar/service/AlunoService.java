package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
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

    public AlunoEntity findById(Long id) {
        log.info("Buscando aluno com id {}", id);
        AlunoEntity aluno = alunoComponent.findById(id);
        log.info("Aluno com id {} encontrado", id);
        return aluno;
    }

//    public AlunoInformacoesDTO findByMatricula(String matricula) {
//        return alunoMapper.toAlunoInformacoesDTO(alunoComponent.findByMatricula(matricula));
//    }

    public AlunoInformacoesDTO save(AlunoPostRequestBody alunoPostRequestBody) {
        log.info("Criando aluno {} no banco de dados", alunoPostRequestBody.getNome());
        AlunoEntity alunoEntity = alunoMapper.toAluno(alunoPostRequestBody);
        AlunoEntity alunoEntitySalvo = alunoComponent.salvar(
                alunoComponent.adicionarMatricula(alunoEntity, gerarMatriculaAluno()));
        log.info("Aluno {} salvo no banco de dados", alunoEntitySalvo.getNome());
        return alunoMapper.toAlunoInformacoesDTO(alunoEntitySalvo);
    }

    public AlunoEntity replace(AlunoPutRequestBody alunoPutRequestBody) {
        log.info("Buscando aluno com id {} no banco de dados", alunoPutRequestBody.getId());
        alunoComponent.findById(alunoPutRequestBody.getId());
        AlunoEntity alunoAtualizado = alunoComponent.salvar(alunoMapper.toAluno(alunoPutRequestBody));
        log.info("Aluno com id {} atualizado", alunoAtualizado.getId());
        return alunoAtualizado;
    }

    public void delete(Long id) {
        log.info("Deletando usuário com id {}", id);
        alunoComponent.deletar(id);
        log.info("Aluno com id {} deletado", id);
    }



    public String gerarMatriculaAluno() {
        StringBuilder matricula = new StringBuilder("SAA");
        LocalDate data = LocalDate.now();
        matricula.append(data.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        long numero = new Random().nextLong(1, 999999);
        matricula.append(String.format("%06d", numero));
        return matricula.toString();
    }
}