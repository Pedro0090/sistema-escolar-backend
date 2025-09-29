package com.pedro_augusto.sistema_escolar.dtos;

import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorListagemDTO {

    private String nome;
    private String matricula;
    private String cpf;
    private String email;
    private Double salario;
    private SituacaoMatricula situacaoMatricula;
}
