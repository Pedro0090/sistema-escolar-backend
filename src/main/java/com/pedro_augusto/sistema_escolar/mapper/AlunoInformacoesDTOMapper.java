package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.Aluno;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlunoInformacoesDTOMapper {

    AlunoInformacoesDTOMapper INSTANCE = Mappers.getMapper(AlunoInformacoesDTOMapper.class);

    AlunoInformacoesDTO toAlunoInformacoesDTO(Aluno aluno);
}
