package com.pedro_augusto.sistema_escolar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoPostRequestDTO {

    @NotBlank(message = "Campo não pode ser vazio")
    private String rua;

    @NotBlank(message = "Campo não pode ser vazio")
    private String cidade;

    @NotBlank(message = "Campo não pode ser vazio")
    private String bairro;

    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank(message = "Campo não pode ser vazio")
    private String numero;

    private Long alunoId;
}
