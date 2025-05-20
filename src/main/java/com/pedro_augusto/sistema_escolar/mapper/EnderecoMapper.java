package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.EnderecoEntity;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.EnderecoPutRequestAndDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "alunoEntity", target = "aluno")
    EnderecoEntity toEnderecoEntity(EnderecoPostRequestDTO enderecoPostRequestDTO, AlunoEntity alunoEntity);

    @Mapping(source = "aluno.id", target = "alunoId")
    EnderecoPostRequestDTO toEnderecoPostRequestDTO(EnderecoEntity enderecoEntity);

    @Mapping(source = "enderecoPutRequestAndDetailsDTO.id", target = "id")
    @Mapping(source = "alunoEntity", target = "aluno")
    EnderecoEntity toEnderecoEntity(EnderecoPutRequestAndDetailsDTO enderecoPutRequestAndDetailsDTO, AlunoEntity alunoEntity);

    @Mapping(source = "aluno.id", target = "alunoId")
    EnderecoPutRequestAndDetailsDTO toEnderecoPutRequestAndDetailsDTO(EnderecoEntity enderecoEntity);
}
