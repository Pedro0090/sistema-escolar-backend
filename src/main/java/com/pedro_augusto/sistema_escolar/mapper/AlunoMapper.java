package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoPutRequestAndDetailsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoEntity toAluno(AlunoPostRequestDTO alunoPostRequestDTO);

    AlunoPostRequestDTO toAlunoPostRequestDTO(AlunoEntity alunoEntity);


    AlunoEntity toAluno(AlunoPutRequestAndDetailsDTO alunoPutRequestAndDetailsDTO);

    AlunoPutRequestAndDetailsDTO toAlunoPutRequestAndDetailsDTO(AlunoEntity alunoEntity);


    AlunoListagemDTO toAlunoListagemDTO(AlunoEntity alunoEntity);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<AlunoEntity> alunoEntities);
}
