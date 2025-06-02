package com.pedro_augusto.sistema_escolar.swagger;

import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Aluno")
public interface AlunoControllerSwagger {

    @Operation(summary = "Lista todos os alunos", description = "Endpoint que retorna todos os alunos em uma lista")
    ResponseEntity<List<AlunoListagemDTO>> findAll();


    @Operation(summary = "Procura um aluno pela matrícula", description = "Endpoint que recupera os" +
            "dados de um aluno com base no código da matrícula ")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    ResponseEntity<AlunoDTO> findByMatricula(@Parameter(description = "Combinação de caracteres para" +
    " matricula", required = true) @PathVariable("matricula") String matricula);


    @Operation(summary = "Cria um novo aluno", description = "Endpoint que cria um novo aluno com base nas" +
            " informações passadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada")
    })
    ResponseEntity<AlunoDTO> save(@RequestBody @Valid @Schema(description = "Informações do aluno"
    ) AlunoDTO alunoDTO);


    @Operation(summary = "Atualiza um aluno já existente", description = "Endpoint que modifica/atualiza" +
            " parte dos dados de um aluno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    ResponseEntity<AlunoDTO> replace(@RequestBody @Valid @Schema(description = "Informações do aluno"
    ) AlunoDTO alunoDTO);


    @Operation(summary = "Deleta um aluno", description = "Deleta um aluno válido e existente com seus dados, atrvés" +
            " da sua matricula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno encontrado e deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "matricula do aluno") String matricula);
}
