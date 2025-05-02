package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetails;
import com.pedro_augusto.sistema_escolar.service.AlunoService;
import com.pedro_augusto.sistema_escolar.swagger.AlunoControllerSwagger;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@Log4j2
public class AlunoController implements AlunoControllerSwagger {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> findAll() {
        log.info("GET /alunos - Listando todos os alunos");
        List<AlunoListagemDTO> alunos = alunoService.listAll();
        log.info("GET /alunos - {} alunos encontrados", alunos.size());
        return ResponseEntity.ok(alunos);
    }

    @GetMapping(path = "/{matricula}")
    public ResponseEntity<AlunoPutRequestAndDetails> findByMatricula(@PathVariable("matricula") String matricula) {
        log.info("GET /alunos/{} - Buscando aluno", matricula);
        AlunoPutRequestAndDetails aluno = alunoService.findByMatricula(matricula);
        log.info("GET /alunos/{} - Aluno encontrado", matricula);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoPostRequestBody> save(@RequestBody @Valid AlunoPostRequestBody alunoPostRequestBody) {
        log.info("POST /alunos - Criando aluno {}", alunoPostRequestBody.getNome());
        AlunoPostRequestBody alunoSalvo = alunoService.save(alunoPostRequestBody);
        log.info("POST /alunos - Aluno {} criado", alunoSalvo.getNome());
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoPutRequestAndDetails> replace(@RequestBody @Valid AlunoPutRequestAndDetails alunoPutRequestAndDetails) {
        log.info("PUT /alunos - Atualizando aluno com matricula {}", alunoPutRequestAndDetails.getMatricula());
        AlunoPutRequestAndDetails alunoAtualizado = alunoService.replace(alunoPutRequestAndDetails);
        log.info("PUT /alunos - Aluno com matricula {} atualizado", alunoAtualizado.getMatricula());
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{matricula}")
    public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
        log.info("DELETE /alunos/{} - Deletando aluno", matricula);
        alunoService.delete(matricula);
        log.info("DELETE /alunos/{} - Aluno deletado", matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
