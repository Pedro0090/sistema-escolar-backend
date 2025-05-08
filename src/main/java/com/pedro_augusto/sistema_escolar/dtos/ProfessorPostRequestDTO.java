package com.pedro_augusto.sistema_escolar.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProfessorPostRequestDTO {

    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 3)
    private String nome;

    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Campo não pode ser vazio")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @NotEmpty(message = "Campo não pode ser vazio")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Telefone inválido! Use o formato (99) 99999-9999")
    private String telefone;

    @NotBlank(message = "Campo não pode ser vazio")
    @Email
    private String email;

    @NotNull(message = "Campo não pode ser vazio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Campo não pode ser vazio")
    @Enumerated(EnumType.STRING)
    private SituacaoMatricula situacaoMatricula;

    @NotNull(message = "Campo não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salário não pode ser negativo")
    private Double salario;
}
