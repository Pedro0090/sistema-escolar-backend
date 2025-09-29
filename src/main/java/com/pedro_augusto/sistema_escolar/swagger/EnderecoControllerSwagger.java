package com.pedro_augusto.sistema_escolar.swagger;

import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoListagemDTO;
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

@Tag(name = "Endereco")
public interface EnderecoControllerSwagger {

    @Operation(summary = "Lista todos os enderecos", description = "Endpoint que retorna todos os enderecos em uma lista")
    ResponseEntity<List<EnderecoListagemDTO>> findAll();


    @Operation(summary = "Procura um endereco pelo ID", description = "Endpoint que recupera os" +
            "dados de um endereco com base no ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enereco encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, endereco não encontrado")
    })
    ResponseEntity<EnderecoDTO> findById(@Parameter(description = "ID do endereco", example = "1, 2, 3",
            required = true) Long id);


    @Operation(summary = "Cria um novo endereco", description = "Endpoint que cria um novo endereco com base nas" +
            " informações passadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco criado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada")
    })
    ResponseEntity<EnderecoDTO> save(@Schema(description = "Informações do endereco"
    ) EnderecoDTO enderecoDTO);


    @Operation(summary = "Atualiza um endereco já existente", description = "Endpoint que modifica/atualiza" +
            " parte dos dados de um endereco existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, endereco não encontrado")
    })
    ResponseEntity<EnderecoDTO> replace(@Schema(description = "Informações do endereco"
    ) EnderecoDTO enderecoDTO);


    @Operation(summary = "Deleta um endereco", description = "Deleta um endereco válido e existente com seus dados, atrvés" +
            " do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereco encontrado e deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, endereco não encontrado")
    })
    ResponseEntity<Void> delete(@Parameter(description = "id do endereco", example = "1, 2, 3") Long id);
}
