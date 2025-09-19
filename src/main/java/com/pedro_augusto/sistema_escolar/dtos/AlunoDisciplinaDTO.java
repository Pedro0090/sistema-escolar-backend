package com.pedro_augusto.sistema_escolar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDisciplinaDTO {

    private Long id;

    private Long alunoId;

    private Long disciplinaId;
}
