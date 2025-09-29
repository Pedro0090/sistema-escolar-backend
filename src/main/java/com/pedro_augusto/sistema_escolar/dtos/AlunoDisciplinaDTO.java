package com.pedro_augusto.sistema_escolar.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDisciplinaDTO {

    private Long id;

    private Long alunoId;

    private Long disciplinaId;
}
