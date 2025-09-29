package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "disciplinas", ignore = true)
    AlunoEntity toAluno(AlunoDTO alunoDTO);

    AlunoDTO toAlunoDTO(AlunoEntity alunoEntity);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<AlunoEntity> alunoEntities);

    default List<Long> mapAlunoDisciplinaParaIds(List<AlunoDisciplinaEntity> relacionamentos) {
        if (relacionamentos == null) return null;
        return relacionamentos.stream()
                .map(relacionamento -> relacionamento.getDisciplinaEntity().getId()).toList();
    }
}
