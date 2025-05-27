package com.pedro_augusto.sistema_escolar.swagger;

import com.pedro_augusto.sistema_escolar.dtos.*;
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

@Tag(name = "Professor")
public interface ProfessorControllerSwagger {

    @Operation(summary = "Lista todos os professores", description = "Endpoint que retorna todos os professores" +
            " em uma lista")
    ResponseEntity<List<ProfessorListagemDTO>> findAll();


    @Operation(summary = "Procura um professor pela matrícula", description = "Endpoint que recupera os" +
            "dados de um professor com base no código da matrícula ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, professor não encontrado")
    })
    ResponseEntity<ProfessorPutRequestAndDetailsDTO> findByMatricula(@Parameter(description = "Combinação de caracteres" +
            " para matricula", required = true) @PathVariable("matricula") String matricula);


    @Operation(summary = "Cria um novo professor", description = "Endpoint que cria um novo professor com base nas" +
            " informações passadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor criado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada")
    })
    ResponseEntity<ProfessorPostRequestDTO> save(@RequestBody @Valid @Schema(description = "Informações do professor"
    ) ProfessorPostRequestDTO professorPostRequestDTO);


    @Operation(summary = "Atualiza um professor já existente", description = "Endpoint que modifica/atualiza" +
            " parte dos dados de um professor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, professor não encontrado")
    })
    ResponseEntity<ProfessorPutRequestAndDetailsDTO> replace(@RequestBody @Valid @Schema(description = "Informações do" +
    " professor") ProfessorPutRequestAndDetailsDTO professorPutRequestAndDetailsDTO);


    @Operation(summary = "Deleta um professor", description = "Deleta um professor válido e existente com seus dados, " +
            "atrvés da sua matricula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Professor encontrado e deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, professor não encontrado")
    })
    ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "matricula do professor") String matricula);
}
