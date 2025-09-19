package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import com.pedro_augusto.sistema_escolar.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        List<DisciplinaDTO> disciplinas = disciplinaService.listAll();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinaDTO> findById(@PathVariable("id") Long id) {
        DisciplinaDTO disciplina = disciplinaService.findById(id);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> save(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO disciplina = disciplinaService.save(disciplinaDTO);
        return new ResponseEntity<>(disciplina, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DisciplinaDTO> replace(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO disciplina = disciplinaService.replace(disciplinaDTO);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        disciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
