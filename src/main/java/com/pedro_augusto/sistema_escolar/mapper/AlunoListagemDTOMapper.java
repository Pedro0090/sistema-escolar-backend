package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoListagemDTOMapper {

    AlunoListagemDTO toAlunoListagemDTO(AlunoEntity alunoEntity);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<AlunoEntity> alunoEntities);
}
