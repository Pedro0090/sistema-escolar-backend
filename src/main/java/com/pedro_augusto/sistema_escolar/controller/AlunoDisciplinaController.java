package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.dtos.AlunoDisciplinaDTO;
import com.pedro_augusto.sistema_escolar.service.AlunoDisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
