package com.pedro_augusto.sistema_escolar.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoInformacoesDTO {

    private String nome;
    private Integer matricula;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String curso;
    private String situacaoMatricula;
}
