package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPutRequestAndDetailsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorEntity toProfessor(ProfessorPostRequestDTO professorPostRequestDTO);

    ProfessorPostRequestDTO toProfessorRequestDTO(ProfessorEntity professorEntity);

    ProfessorEntity toProfessor(ProfessorPutRequestAndDetailsDTO professorPutRequestAndDetailsDTO);

    ProfessorPutRequestAndDetailsDTO toProfessorPutRequestAndDetailsDTO(ProfessorEntity professorEntity);

    ProfessorListagemDTO toProfessorListagemDTO(ProfessorEntity professorEntity);

    List<ProfessorListagemDTO> toListProfessorListagemDTO(List<ProfessorEntity> professorEntityList);
}
