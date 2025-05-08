package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> findAll() {
        List<ProfessorEntity> listaProfessores = professorService.listAll();
        return ResponseEntity.ok(listaProfessores);
    }

    @GetMapping(path = "/{matricula}")
    public ResponseEntity<ProfessorPutRequestAndDetailsDTO> findByMatricula(@PathVariable("matricula") String matricula) {
        ProfessorPutRequestAndDetailsDTO professor = professorService.findByMatricula(matricula);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorPostRequestDTO> save(@RequestBody @Valid ProfessorPostRequestDTO professorPostRequestDTO) {
        ProfessorPostRequestDTO professor = professorService.save(professorPostRequestDTO);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProfessorPutRequestAndDetailsDTO> replace( @RequestBody @Valid
            ProfessorPutRequestAndDetailsDTO professorPutRequestAndDetailsDTO) {
        ProfessorPutRequestAndDetailsDTO professor = professorService.replace(professorPutRequestAndDetailsDTO);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{matricula}")
    public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
        professorService.delete(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
