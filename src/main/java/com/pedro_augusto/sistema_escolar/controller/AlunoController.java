package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoEntity> findById(@PathVariable("id") Long id) {
        log.info("GET /alunos/{} - Buscando usuário", id);
        AlunoEntity aluno = alunoService.findById(id);
        log.info("GET /alunos/{} - Aluno encontrado", id);
        return ResponseEntity.ok(aluno);
    }

//    @GetMapping(path = "/buscar-matricula/{matricula}")
//    public ResponseEntity<AlunoInformacoesDTO> findByMatricula(@PathVariable("matricula") String matricula) {
//        return ResponseEntity.ok(alunoService.findByMatricula(matricula));
//    }

    @PostMapping
    public ResponseEntity<AlunoInformacoesDTO> save(@RequestBody @Valid AlunoPostRequestBody alunoPostRequestBody) {
        log.info("POST /alunos - Criando aluno {}", alunoPostRequestBody.getNome());
        AlunoInformacoesDTO alunoSalvo  = alunoService.save(alunoPostRequestBody);
        log.info("POST /alunos - Aluno {} criado", alunoSalvo.getNome());
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoEntity> replace(@RequestBody @Valid AlunoPutRequestBody alunoPutRequestBody) {
        log.info("PUT /alunos - Atualizando aluno com id {}", alunoPutRequestBody.getId());
        AlunoEntity alunoAtualizado = alunoService.replace(alunoPutRequestBody);
        log.info("PUT /alunos - Aluno com id {} atualizado", alunoAtualizado.getId());
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("DELETE /alunos/{} - Deletando aluno", id);
        alunoService.delete(id);
        log.info("DELETE /alunos/{} - Aluno deletado", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
