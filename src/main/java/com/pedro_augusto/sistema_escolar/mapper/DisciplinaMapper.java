package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(source = "disciplinaDTO.id", target = "id")
    @Mapping(source = "disciplinaDTO.nome", target = "nome")
    @Mapping(source = "professorEntity", target = "professor")
    DisciplinaEntity toDisciplinaEntity(DisciplinaDTO disciplinaDTO, ProfessorEntity professorEntity);

    @Mapping(source = "professor.id", target = "professorId")
    DisciplinaDTO toDisciplinaDTO(DisciplinaEntity disciplinaEntity);

    List<DisciplinaDTO> toListDisciplinaDTO(List<DisciplinaEntity> disciplinaEntities);
}
