package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.Aluno;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
import com.pedro_augusto.sistema_escolar.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @Operation(summary = "Lista todos os alunos", description = "Endpoint que retorna todos os alunos e uma lista")
    @GetMapping(path = "/all")
    public ResponseEntity<List<AlunoListagemDTO>> findAll() {
        return ResponseEntity.ok(alunoService.listAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    @Operation(summary = "Procura um aluno pelo ID", description = "Endpoint que recupera os dados de um aluno " +
            "com base no id")
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Aluno> findById(@Parameter(description = "ID do aluno", example = "1,2,3",
    required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    @Operation(summary = "Procura um aluno pelo número da matrícula", description = "Endpoint que recupera os" +
            "dados de um aluno com base no número da matrícula, sendo de 100000 a 999999")
    @GetMapping(path = "/matricula/{matricula}")
    public ResponseEntity<AlunoInformacoesDTO> findByMatricula(@Parameter(description = "Número da " +
            "matrícula é um número com 5 unidades",
    example = "100000, 999999", required = true) @PathVariable("matricula") Integer numeroMatricula) {
        return ResponseEntity.ok(alunoService.findByMatricula(numeroMatricula));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada")
    })
    @Operation(summary = "Cria um novo aluno", description = "Endpoint que cria um novo aluno com base nas" +
            " informações passadas")
    @PostMapping
    public ResponseEntity<AlunoListagemDTO> save(@RequestBody @Valid @Schema(description = "Informações do aluno"
    ) AlunoPostRequestBody alunoPostRequestBody) {
        return new ResponseEntity<>(alunoService.save(alunoPostRequestBody), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    @Operation(summary = "Atualiza um aluno já existente", description = "Endpoint que modifica/atualiza" +
            " parte dos dados de um aluno existente")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid @Schema(description = "Informações do aluno"
    ) AlunoPutRequestBody alunoPutRequestBody) {
        alunoService.replace(alunoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno encontrado e deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição Errada, aluno não encontrado")
    })
    @Operation(summary = "Deleta um aluno", description = "Deleta um aluno válido e existente com seus dados, atrvés" +
            " do seu ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "ID do aluno", example =
    "1,2,3") Long id) {
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
