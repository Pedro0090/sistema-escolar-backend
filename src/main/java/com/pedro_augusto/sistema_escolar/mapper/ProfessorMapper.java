package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorListagemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorEntity toProfessor(ProfessorDTO professorDTO);

    ProfessorDTO toProfessorDTO(ProfessorEntity professorEntity);

    ProfessorListagemDTO toProfessorListagemDTO(ProfessorEntity professorEntity);

    List<ProfessorListagemDTO> toListProfessorListagemDTO(List<ProfessorEntity> professorEntityList);
}
