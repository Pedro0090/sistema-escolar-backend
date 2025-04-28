package com.pedro_augusto.sistema_escolar.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AlunoListagemDTO {

    private Long id;
    private String nome;
    private String matricula;
}
