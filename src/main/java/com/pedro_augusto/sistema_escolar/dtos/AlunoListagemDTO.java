package com.pedro_augusto.sistema_escolar.dtos;

import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AlunoListagemDTO {

    private String nome;
    private String matricula;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String curso;
    private SituacaoMatricula situacaoMatricula;
}
