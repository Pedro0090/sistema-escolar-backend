package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDisciplinaDTO;
import com.pedro_augusto.sistema_escolar.service.AlunoDisciplinaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rels")
public class AlunoDisciplinaController {

    private final AlunoDisciplinaService alunoDisciplinaService;

    @Autowired
    public AlunoDisciplinaController(AlunoDisciplinaService alunoDisciplinaService) {
        this.alunoDisciplinaService = alunoDisciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDisciplinaDTO>> findAll() {
        return ResponseEntity.ok(alunoDisciplinaService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoDisciplinaDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoDisciplinaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDisciplinaDTO> save(@RequestBody AlunoDisciplinaDTO alunoDisciplinaDTO) {
        return new ResponseEntity<>(alunoDisciplinaService.save(alunoDisciplinaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoDisciplinaDTO> replace(@Valid @RequestBody AlunoDisciplinaDTO alunoDisciplinaDTO) {
        return new ResponseEntity<>(alunoDisciplinaService.save(alunoDisciplinaDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        alunoDisciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
