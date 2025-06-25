package com.pedro_augusto.sistema_escolar.controller;

import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoListagemDTO;
import com.pedro_augusto.sistema_escolar.service.EnderecoService;
import com.pedro_augusto.sistema_escolar.swagger.EnderecoControllerSwagger;
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
public class EnderecoController implements EnderecoControllerSwagger {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoListagemDTO>> findAll() {
        log.info("GET /enderecos - Listando todos os endereços");
        List<EnderecoListagemDTO> listaEnderecos = enderecoService.listAll();
        log.info("GET /enderecos - {} endereços encontrados", listaEnderecos.size());
        return ResponseEntity.ok(listaEnderecos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("id") Long id) {
        log.info("GET /enderecos/{} - Buscando endereço", id);
        EnderecoDTO endereco = enderecoService.findById(id);
        log.info("GET /enderecos/{} - Endereço encontrado", id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        log.info("POST /enderecos - Criando endereço");
        EnderecoDTO endereco = enderecoService.save(enderecoDTO);
        log.info("POST /enderecos - endereco criado");
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> replace(@RequestBody @Valid EnderecoDTO
                                                       enderecoDTO) {
        log.info("PUT /enderecos - Atualizando endereco com id {}",
                enderecoDTO.getId());
        EnderecoDTO enderecoAtualizado = enderecoService.replace(enderecoDTO);
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
