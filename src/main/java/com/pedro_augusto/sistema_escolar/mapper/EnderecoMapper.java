package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "enderecoDTO.id", target = "id")
    @Mapping(source = "alunoEntity", target = "aluno")
    EnderecoEntity toEnderecoEntity(EnderecoDTO enderecoDTO, AlunoEntity alunoEntity);

    @Mapping(source = "aluno.id", target = "alunoId")
    EnderecoDTO toEnderecoDTO(EnderecoEntity enderecoEntity);

    @Mapping(source = "aluno.id", target = "alunoId")
    EnderecoListagemDTO toEnderecoListagemDTO(EnderecoEntity enderecoEntity);

    List<EnderecoListagemDTO> toListEnderecoListagemDTO(List<EnderecoEntity> list);
}
