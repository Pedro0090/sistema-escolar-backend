package com.pedro_augusto.sistema_escolar.dtos;

import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoDisciplina;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DisciplinaDTO {

    private Long id;

    @NotBlank(message = "Campo não pode ser vazio")
    private String nome;

    @NotNull(message = "Campo não pode ser vazio")
    @Enumerated(EnumType.STRING)
    private SituacaoDisciplina situacaoDisciplina;

    private Long professorId;
}
