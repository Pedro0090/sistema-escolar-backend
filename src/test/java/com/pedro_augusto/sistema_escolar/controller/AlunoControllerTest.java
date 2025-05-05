package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.service.AlunoService;
import com.pedro_augusto.sistema_escolar.utils.AlunoCreator;
import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("findAll retorna uma lista com todos os alunos quando bem sucedido")
    void findAll_retornaListaAlunoListagemDTO_QuandoBemSucedido() {
        BDDMockito.when(alunoServiceMock.listAll()).thenReturn(List.of(AlunoCreator.criarAlunoListagemDTOValido()));

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
    @DisplayName("findByMatricula retorna um aluno quando bem sucedido")
    void findByMatricula_retornaAlunoPutRequestAndDetails_QuandoBemSucedido() {
        BDDMockito.when(alunoServiceMock.findByMatricula(ArgumentMatchers.anyString()))
                .thenReturn(AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido());

        String matriculaEsperada = AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido().getMatricula();
        Long idEsperado = AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido().getId();

        ResponseEntity<AlunoPutRequestAndDetailsDTO> aluno = alunoController.findByMatricula("SAA23042025000001");

        Assertions.assertThat(aluno.getBody()).isNotNull();
        Assertions.assertThat(aluno.getBody().getMatricula()).isEqualTo(matriculaEsperada);
        Assertions.assertThat(aluno.getBody().getId()).isEqualTo(idEsperado);
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findByMatricula retorna BadRequest quando aluno não encontrado")
    void findByMatricula_retornaBadRequest_QuandoAlunoNaoEncontrado() {
        BDDMockito.when(alunoServiceMock.findByMatricula(ArgumentMatchers.anyString()))
                .thenThrow(new BadRequestException("Aluno não encontrado"));

        Assertions.assertThatThrownBy(() -> alunoController.findByMatricula("AAAA"))
                .isInstanceOf(BadRequestException.class).hasMessage("Aluno não encontrado");
    }

    @Test
    @DisplayName("save retorna um aluno quando bem sucedido")
    void save_retornaAlunoPostRequestBody_QuandoBemSucedido() {
        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any(AlunoPostRequestDTO.class)))
                .thenReturn(AlunoCreator.criarAlunoPostRequestDTOValido());

        ResponseEntity<AlunoPostRequestDTO> aluno =
                alunoController.save(AlunoCreator.criarAlunoPostRequestDTOValido());

        Assertions.assertThat(aluno.getBody()).isNotNull().isEqualTo(AlunoCreator.criarAlunoPostRequestDTOValido());
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("save retorna BadRequest quando nome null")
    void save_retornaBadRequest_QuandoNomeNull() {
        AlunoPostRequestDTO alunoInvalido = AlunoCreator.criarAlunoPostRequestDTOValido();
        alunoInvalido.setNome(null);

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any(AlunoPostRequestDTO.class)))
                .thenThrow(new BadRequestException("Validation failed for object='alunoPostRequestBody'"));

        Assertions.assertThatThrownBy(() -> alunoController.save(alunoInvalido)).isInstanceOf(BadRequestException.class)
                .hasMessage("Validation failed for object='alunoPostRequestBody'");
    }

    @Test
    @DisplayName("replace atualiza um aluno quando bem sucedido")
    void replace_retornaAlunoPutRequestAndDetails_QuandoBemSucedido() {
        BDDMockito.when(alunoServiceMock.replace(ArgumentMatchers.any(AlunoPutRequestAndDetailsDTO.class)))
                .thenReturn(AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido());

        ResponseEntity<AlunoPutRequestAndDetailsDTO> aluno =
                alunoController.replace(AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido());

        Assertions.assertThat(aluno.getBody()).isNotNull().isEqualTo(
                AlunoCreator.criarAlunoPutRequestAndDetailsDTOValido());
        Assertions.assertThat(aluno.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete deleta um aluno quando bem sucedido")
    void delete_deletaAluno_QuandoBemSucedido() {
        BDDMockito.doNothing().when(alunoServiceMock).delete(ArgumentMatchers.anyString());

        ResponseEntity<Void> body = alunoController.delete("SAA23042025000001");

        Assertions.assertThatCode(() -> alunoController.delete("SAA23042025000001")).doesNotThrowAnyException();

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete retorna BadRequest quando aluno não é encontrado")
    void delete_retornaBadRequest_QuandoAlunoNaoEncontrado() {
        BDDMockito.doThrow(new BadRequestException("Aluno não encontrado")).when(alunoServiceMock)
                .delete(ArgumentMatchers.anyString());


        Assertions.assertThatThrownBy(() -> alunoServiceMock.delete("AAA"))
                .isInstanceOf(BadRequestException.class).hasMessage("Aluno não encontrado");
    }
}