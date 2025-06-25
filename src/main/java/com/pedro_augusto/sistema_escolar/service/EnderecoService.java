package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.component.EnderecoComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoListagemDTO;
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

    public List<EnderecoListagemDTO> listAll() {
        log.info("Buscando endereços no banco de dados");
        List<EnderecoListagemDTO> enderecos = enderecoMapper.toListEnderecoListagemDTO(enderecoComponent.findAll());
        log.info("{} endereços encontrados", enderecos.size());
        return enderecos;
    }

    public EnderecoDTO findById(Long id) {
        log.info("Buscando endereco com id {}", id);
        EnderecoEntity endereco = enderecoComponent.findById(id)
                .orElseThrow(() -> new BadRequestException("Endereço não encontrado"));
        log.info("Endereço com id {} encontrado", id);
        return enderecoMapper.toEnderecoDTO(endereco);
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        log.info("Criando endereco no banco de dados");
        AlunoEntity alunoSalvo = alunoComponent.findById(enderecoDTO.getAlunoId());
        EnderecoEntity enderecoEntity = enderecoMapper.toEnderecoEntity(enderecoDTO, alunoSalvo);
        EnderecoEntity enderecoSalvo = enderecoComponent.salvar(enderecoEntity);
        log.info("Endereco salvo no banco de dados");
        return enderecoMapper.toEnderecoDTO(enderecoSalvo);
    }

    public EnderecoDTO replace(EnderecoDTO enderecoDTO) {
        log.info("Buscando endereço com id {} no banco de dados", enderecoDTO.getId());
        enderecoComponent.findById(enderecoDTO.getId());
        AlunoEntity alunoSalvo = alunoComponent.findById(enderecoDTO.getAlunoId());
        EnderecoEntity enderecoAtualizado = enderecoComponent.salvar(
                enderecoMapper.toEnderecoEntity(enderecoDTO, alunoSalvo)
        );
        log.info("Endereço com id {} atualizado", enderecoAtualizado.getId());
        return enderecoMapper.toEnderecoDTO(enderecoAtualizado);
    }

    public void delete(Long id) {
        log.info("Deletando endereço de id {}", id);
        enderecoComponent.deletar(id);
        log.info("Enderço com id {} deletado", id);
    }
}
