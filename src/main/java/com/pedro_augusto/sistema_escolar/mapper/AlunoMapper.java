package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoEntity toAluno(AlunoDTO alunoDTO);

    AlunoDTO toAlunoDTO(AlunoEntity alunoEntity);

    AlunoListagemDTO toAlunoListagemDTO(AlunoEntity alunoEntity);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<AlunoEntity> alunoEntities);
}
