package com.pedro_augusto.sistema_escolar.utils;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;

import java.time.LocalDate;

public class AlunoCreator {

    public static AlunoEntity criarAlunoValido() {
        return AlunoEntity.builder().id(1L).nome("Fernando Silva")
                .cpf("47730066092")
                .matricula("SAA23042025000001")
                .telefone("(33) 98765-4321")
                .email("fernando02@gmail.com")
                .dataNascimento(LocalDate.of(1998, 7, 22))
                .sexo(Sexo.M)
                .nomeMae("Rafaela Silva")
                .nomePai("Marcos Silva")
                .situacaoMatricula(SituacaoMatricula.ATIVO)
                .curso("Engenharia de Alimentos")
                .build();
    }

    public static AlunoListagemDTO criarAlunoListagemDTOValido() {
        return AlunoListagemDTO.builder().id(1L).nome("Fernando Silva").matricula("SAA23042025000001").build();
    }

    public static AlunoPostRequestBody criarAlunoPostRequestBodyValido() {
        return AlunoPostRequestBody.builder().nome("Fernando Silva")
                .cpf("47730066092")
                .matricula("SAA23042025000001")
                .telefone("(33) 98765-4321")
                .email("fernando02@gmail.com")
                .dataNascimento(LocalDate.of(1998, 7, 22))
                .sexo(Sexo.M)
                .nomeMae("Rafaela Silva")
                .nomePai("Marcos Silva")
                .situacaoMatricula(SituacaoMatricula.ATIVO)
                .curso("Engenharia de Alimentos")
                .build();
    }

    public static AlunoInformacoesDTO criarAlunoInformacoesDTOValido() {
        return AlunoInformacoesDTO.builder().nome("Fernando Silva")
                .matricula("SAA23042025000001")
                .telefone("(33) 98765-4321")
                .email("fernando02@gmail.com")
                .dataNascimento(LocalDate.of(1998, 7, 22))
                .curso("Engenharia de Alimentos")
                .situacaoMatricula((SituacaoMatricula.ATIVO))
                .build();
    }

    public static AlunoPutRequestBody criarAlunoPutRequestBodyValido() {
        return AlunoPutRequestBody.builder().id(1L).nome("Fernando Silva")
                .cpf("47730066092")
                .matricula("SAA23042025000001")
                .telefone("(33) 98765-4321")
                .email("fernando02@gmail.com")
                .dataNascimento(LocalDate.of(1998, 7, 22))
                .sexo(Sexo.M)
                .nomeMae("Rafaela Silva")
                .nomePai("Marcos Silva")
                .situacaoMatricula(SituacaoMatricula.ATIVO)
                .curso("Engenharia de Alimentos")
                .build();
    }
}
