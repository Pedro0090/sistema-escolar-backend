package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.domain.Aluno;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.AlunoInformacoesDTOMapper;
import com.pedro_augusto.sistema_escolar.mapper.AlunoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoComponent alunoComponent;

    public List<AlunoListagemDTO> listAll() {
         List<Aluno> lista = alunoComponent.findAll();
        return AlunoMapper.INSTANCE.toListAlunoListagemDTO(lista);
    }

    public Aluno findById(Long id) throws BadRequestException {
        return alunoComponent.findById(id);
    }

    public AlunoInformacoesDTO findByMatricula(Integer matricula) {
        return AlunoInformacoesDTOMapper.INSTANCE.toAlunoInformacoesDTO(alunoComponent.findMatricula(matricula));
    }

    public AlunoListagemDTO save(AlunoPostRequestBody alunoPostRequestBody) {
        Aluno aluno = AlunoMapper.INSTANCE.toAluno(alunoPostRequestBody);
        aluno.setMatricula(gerarNumeroMatriculaValida());
        return AlunoMapper.INSTANCE.toAlunoListagemDTO(alunoComponent.salvar(aluno));
    }

    public void replace(AlunoPutRequestBody alunoPutRequestBody) {
        Aluno alunoSalvo = findById(AlunoMapper.INSTANCE.toAluno(alunoPutRequestBody).getId());
        Aluno alunoModificado = AlunoMapper.INSTANCE.toAluno(alunoPutRequestBody);
        alunoModificado.setId(alunoSalvo.getId());
        alunoModificado.setMatricula(alunoSalvo.getMatricula());
        alunoComponent.salvar(alunoModificado);
    }

    public void delete(Long id) {
        alunoComponent.deletar(id);
    }



    private Integer gerarNumeroMatriculaValida() {
        Random generator = new Random();
        Integer numeroMatricula;
        do {
            numeroMatricula = generator.nextInt(100000, 999999);
        } while (findByMatricula(numeroMatricula) != null);
        return numeroMatricula;
    }
}