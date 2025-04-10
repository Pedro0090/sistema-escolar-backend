package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.Aluno;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    Aluno toAluno(AlunoPostRequestBody alunoPostRequestBody);

    Aluno toAluno(AlunoPutRequestBody alunoPutRequestbody);

    AlunoListagemDTO toAlunoListagemDTO(Aluno aluno);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<Aluno> alunos);



}
