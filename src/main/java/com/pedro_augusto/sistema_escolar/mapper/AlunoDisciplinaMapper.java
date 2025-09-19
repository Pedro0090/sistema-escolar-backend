package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDisciplinaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoDisciplinaMapper {

    @Mapping(source = "alunoEntity.id", target = "alunoId")
    @Mapping(source = "disciplinaEntity.id", target = "disciplinaId")
    AlunoDisciplinaDTO toAlunoDisciplinaDTO(AlunoDisciplinaEntity alunoDisciplinaEntity);

    List<AlunoDisciplinaDTO> toListAlunoDisciplinaDTO(List<AlunoDisciplinaEntity> alunoDisciplinaEntities);
}
