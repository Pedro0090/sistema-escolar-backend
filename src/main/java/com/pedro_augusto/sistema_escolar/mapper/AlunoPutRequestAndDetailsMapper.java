package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoPutRequestAndDetailsMapper {

    AlunoEntity toAluno(AlunoPutRequestAndDetails alunoPutRequestAndDetails);

    AlunoPutRequestAndDetails toAlunoPutRequestAndDetails(AlunoEntity alunoEntity);

}
