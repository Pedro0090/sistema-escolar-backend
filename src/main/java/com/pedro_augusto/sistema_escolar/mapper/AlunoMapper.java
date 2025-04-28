package com.pedro_augusto.sistema_escolar.mapper;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoInformacoesDTO;
import com.pedro_augusto.sistema_escolar.dtos.AlunoListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPostRequestBody;
import com.pedro_augusto.sistema_escolar.dtos.requests.AlunoPutRequestBody;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoEntity toAluno(AlunoPostRequestBody alunoPostRequestBody);

    AlunoEntity toAluno(AlunoPutRequestBody alunoPutRequestbody);

    AlunoListagemDTO toAlunoListagemDTO(AlunoEntity alunoEntity);

    List<AlunoListagemDTO> toListAlunoListagemDTO(List<AlunoEntity> alunoEntities);

    AlunoInformacoesDTO toAlunoInformacoesDTO(AlunoEntity alunoEntity);

}
