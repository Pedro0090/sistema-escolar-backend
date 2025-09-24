package com.pedro_augusto.sistema_escolar.dtos;

import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
