package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoPostRequestMapper {

    AlunoEntity toAluno(AlunoPostRequestBody alunoPostRequestBody);

    AlunoPostRequestBody toAlunoPostRequestBody(AlunoEntity alunoEntity);
}
