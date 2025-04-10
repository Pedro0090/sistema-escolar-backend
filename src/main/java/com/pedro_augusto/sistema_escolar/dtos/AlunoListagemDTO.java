package com.pedro_augusto.sistema_escolar.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoListagemDTO {

    private Long id;
    private String nome;
    private Integer matricula;
}
