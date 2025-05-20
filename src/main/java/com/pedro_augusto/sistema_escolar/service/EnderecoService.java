package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.component.EnderecoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.EnderecoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EnderecoService {

    private final EnderecoComponent enderecoComponent;
    private final EnderecoMapper enderecoMapper;
    private final AlunoComponent alunoComponent;

    @Autowired
    public EnderecoService(EnderecoComponent enderecoComponent, EnderecoMapper enderecoMapper, AlunoComponent alunoComponent) {
        this.enderecoComponent = enderecoComponent;
        this.enderecoMapper = enderecoMapper;
        this.alunoComponent = alunoComponent;
    }

    public List<EnderecoEntity> listAll() {
        log.info("Buscando endereços no banco de dados");
        List<EnderecoEntity> enderecos = enderecoComponent.findAll();
        log.info("{} endereços encontrados", enderecos.size());
        return enderecos;
    }

    public EnderecoPutRequestAndDetailsDTO findById(Long id) {
        log.info("Buscando endereco com id {}", id);
        EnderecoEntity endereco = enderecoComponent.findById(id)
                .orElseThrow(() -> new BadRequestException("Endereço não encontrado"));
        log.info("Endereço com id {} encontrado", id);
        return enderecoMapper.toEnderecoPutRequestAndDetailsDTO(endereco);
    }

    public EnderecoPostRequestDTO save(EnderecoPostRequestDTO enderecoPostRequestDTO) {
        log.info("Criando endereco no banco de dados");
        AlunoEntity alunoSalvo = alunoComponent.findById(enderecoPostRequestDTO.getAlunoId());
        EnderecoEntity enderecoEntity = enderecoMapper.toEnderecoEntity(enderecoPostRequestDTO, alunoSalvo);
        EnderecoEntity enderecoSalvo = enderecoComponent.salvar(enderecoEntity);
        log.info("Endereco salvo no banco de dados");
        return enderecoMapper.toEnderecoPostRequestDTO(enderecoSalvo);
    }

    public EnderecoPutRequestAndDetailsDTO replace(EnderecoPutRequestAndDetailsDTO enderecoPutRequestAndDetailsDTO) {
        log.info("Buscando endereço com id {} no banco de dados", enderecoPutRequestAndDetailsDTO.getId());
        enderecoComponent.findById(enderecoPutRequestAndDetailsDTO.getId());
        AlunoEntity alunoSalvo = alunoComponent.findById(enderecoPutRequestAndDetailsDTO.getAlunoId());
        EnderecoEntity enderecoAtualizado = enderecoComponent.salvar(
                enderecoMapper.toEnderecoEntity(enderecoPutRequestAndDetailsDTO, alunoSalvo)
        );
        log.info("Endereço com id {} atualizado", enderecoAtualizado.getId());
        return enderecoMapper.toEnderecoPutRequestAndDetailsDTO(enderecoAtualizado);
    }

    public void delete(Long id) {
        log.info("Deletando endereço de id {}", id);
        enderecoComponent.deletar(id);
        log.info("Enderço com id {} deletado", id);
    }
}
