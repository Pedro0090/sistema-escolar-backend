package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.dtos.ProfessorDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorListagemDTO;
import com.pedro_augusto.sistema_escolar.service.ProfessorService;
import com.pedro_augusto.sistema_escolar.swagger.ProfessorControllerSwagger;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@Log4j2
public class ProfessorController implements ProfessorControllerSwagger {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorListagemDTO>> findAll() {
        log.info("GET /professores - Listando todos os professores");
        List<ProfessorListagemDTO> listaProfessores = professorService.listAll();
        log.info("GET /professores - {} alunos encontrados", listaProfessores.size());
        return ResponseEntity.ok(listaProfessores);
    }

    @GetMapping(path = "/{matricula}")
    public ResponseEntity<ProfessorDTO> findByMatricula(@PathVariable("matricula") String matricula) {
        log.info("GET /professores/{} - Buscando professor", matricula);
        ProfessorDTO professor = professorService.findByMatricula(matricula);
        log.info("GET /professores/{} - Professor encontrado", matricula);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@RequestBody @Valid ProfessorDTO professorDTO) {
        log.info("POST /professores - Criando aluno {}", professorDTO.getNome());
        ProfessorDTO professorSalvo = professorService.save(professorDTO);
        log.info("POST /professores - Aluno {} criado", professorSalvo.getNome());
        return new ResponseEntity<>(professorSalvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> replace(@RequestBody @Valid ProfessorDTO professorDTO) {
        log.info("PUT /professores - Atualizando professor com matricula {}",
                professorDTO.getMatricula());
        ProfessorDTO professorAtualizado = professorService.replace(professorDTO);
        log.info("PUT /professores - Aluno com matricula {} atualizado", professorAtualizado.getMatricula());
        return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{matricula}")
    public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
        log.info("DELETE /professores/{} - Deletando professor", matricula);
        professorService.delete(matricula);
        log.info("DELETE /professores/{} - Professor deletado", matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
