package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/enderecos")
@Log4j2
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoEntity>> findAll() {
        log.info("GET /enderecos - Listando todos os endereços");
        List<EnderecoEntity> listaEnderecos = enderecoService.listAll();
        log.info("GET /enderecos - {} endereços encontrados", listaEnderecos.size());
        return ResponseEntity.ok(listaEnderecos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnderecoPutRequestAndDetailsDTO> findByMatricula(@PathVariable("id") Long id) {
        log.info("GET /enderecos/{} - Buscando endereço", id);
        EnderecoPutRequestAndDetailsDTO endereco = enderecoService.findById(id);
        log.info("GET /enderecos/{} - Endereço encontrado", id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoPostRequestDTO> save(@RequestBody @Valid EnderecoPostRequestDTO enderecoPostRequestDTO) {
        log.info("POST /enderecos - Criando endereço");
        EnderecoPostRequestDTO endereco = enderecoService.save(enderecoPostRequestDTO);
        log.info("POST /enderecos - endereco criado");
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnderecoPutRequestAndDetailsDTO> replace(@RequestBody @Valid EnderecoPutRequestAndDetailsDTO
                                                                               enderecoPutRequestAndDetailsDTO) {
        log.info("PUT /enderecos - Atualizando endereco com id {}",
                enderecoPutRequestAndDetailsDTO.getId());
        EnderecoPutRequestAndDetailsDTO enderecoAtualizado = enderecoService.replace(enderecoPutRequestAndDetailsDTO);
        log.info("PUT /enderecos - Endereço com id {} atualizado", enderecoAtualizado.getId());
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("DELETE /enderecos/{} - Deletando endereço", id);
        enderecoService.delete(id);
        log.info("DELETE /enderecos/{} - Endereço deletado", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
