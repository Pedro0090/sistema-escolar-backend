package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(source = "disciplinaDTO.id", target = "id")
    @Mapping(source = "disciplinaDTO.nome", target = "nome")
    @Mapping(source = "professorEntity", target = "professor")
    @Mapping(target = "alunos", ignore = true)
    DisciplinaEntity toDisciplinaEntity(DisciplinaDTO disciplinaDTO, ProfessorEntity professorEntity);

    @Mapping(source = "professor.id", target = "professorId")
    DisciplinaDTO toDisciplinaDTO(DisciplinaEntity disciplinaEntity);

    List<DisciplinaDTO> toListDisciplinaDTO(List<DisciplinaEntity> disciplinaEntities);

    default List<Long> mapAlunoDisciplinaParaIds(List<AlunoDisciplinaEntity> relacionamentos) {
        if (relacionamentos == null) return null;
        return relacionamentos.stream()
                .map(relacionamento -> relacionamento.getAlunoEntity().getId()).toList();
    }
}
