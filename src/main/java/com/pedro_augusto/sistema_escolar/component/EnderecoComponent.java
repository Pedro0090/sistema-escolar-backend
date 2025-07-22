package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.EndercoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoComponent {

    private final EndercoRepository enderecoRepository;

    @Autowired
    public EnderecoComponent(EndercoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoEntity> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<EnderecoEntity> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public EnderecoEntity salvar(EnderecoEntity enderecoEntity) {
        return enderecoRepository.save(enderecoEntity);
    }

    public void deletar(Long id) {
        EnderecoEntity endereco = findById(id).orElseThrow(() -> new BadRequestException("Endereço não encontrado"));
        enderecoRepository.delete(endereco);
    }
}
