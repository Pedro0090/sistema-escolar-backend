package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
import com.pedro_augusto.sistema_escolar.service.AlunoService;
import com.pedro_augusto.sistema_escolar.utils.AlunoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(alunoServiceMock.listAll()).thenReturn(List.of(AlunoCreator.criarAlunoListagemDTOValido()));

        BDDMockito.when(alunoServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AlunoCreator.criarAlunoValido());

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any(AlunoPostRequestBody.class)))
                .thenReturn(AlunoCreator.criarAlunoInformacoesDTOValido());

        BDDMockito.when(alunoServiceMock.replace(ArgumentMatchers.any(AlunoPutRequestBody.class)))
                .thenReturn(AlunoCreator.criarAlunoValido());

        BDDMockito.doNothing().when(alunoServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("findAll retorna uma lista com todos os alunos quando bem sucedido")
    void findAll_retornaListaAlunos_QuandoBemSucedido() {
        String nomeEsperado = AlunoCreator.criarAlunoListagemDTOValido().getNome();

        ResponseEntity<List<AlunoListagemDTO>> listaAlunos = alunoController.findAll();

        Assertions.assertThat(listaAlunos.getBody()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat((listaAlunos.getBody()).getFirst().getNome()).isEqualTo(nomeEsperado);
        Assertions.assertThat(listaAlunos.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findAll retorna uma lista vazia quando não há nenhum aluno")
    void findAll_retornaListaVazia_QuandoNenhumAluno() {
        BDDMockito.when(alunoServiceMock.listAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<AlunoListagemDTO>> listaAlunos = alunoController.findAll();

        Assertions.assertThat(listaAlunos.getBody()).isNotNull().isEmpty();
        Assertions.assertThat(listaAlunos.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findById retorna um aluno quando bem sucedido")
    void findById_retornaAluno_QuandoBemSucedido() {
        Long idEsperado = AlunoCreator.criarAlunoValido().getId();

        ResponseEntity<AlunoEntity> aluno = alunoController.findById(1L);

        Assertions.assertThat(aluno.getBody()).isNotNull();
        Assertions.assertThat((aluno.getBody()).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("save retorna um aluno quando bem sucedido")
    void save_retornaAluno_QuandoBemSucedido() {
        ResponseEntity<AlunoInformacoesDTO> aluno =
                alunoController.save(AlunoCreator.criarAlunoPostRequestBodyValido());

        Assertions.assertThat(aluno.getBody()).isNotNull().isEqualTo(AlunoCreator.criarAlunoInformacoesDTOValido());
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("replace atualiza um aluno quando bem sucedido")
    void replace_retornaAluno_QuandoBemSucedido() {
        ResponseEntity<AlunoEntity> aluno = alunoController.replace(AlunoCreator.criarAlunoPutRequestBodyValido());

        Assertions.assertThat(aluno.getBody()).isNotNull().isEqualTo(AlunoCreator.criarAlunoValido());
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete deleta um aluno quando bem sucedido")
    void delete_deletaAluno_QuandoBemSucedido() {
        ResponseEntity<Void> body = alunoController.delete(1L);

        Assertions.assertThatCode(() -> alunoController.delete(1L)).doesNotThrowAnyException();

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}