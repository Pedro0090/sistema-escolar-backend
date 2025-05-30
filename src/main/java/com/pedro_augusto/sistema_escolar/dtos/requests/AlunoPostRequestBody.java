package com.pedro_augusto.sistema_escolar.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoPostRequestBody {

    @Size(min = 3, message = "O nome não pode ter menos que 3 letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nome;

    @Schema(description = "CPF do usuário somente com números", example = "00000000000")
    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    private String matricula;

    @Schema(example = "(00) 00000-0000")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Telefone inválido! Use o formato (99) 99999-9999")
    @NotEmpty(message = "Campo não pode ser vazio")
    private String telefone;

    @Email(message = "Email inválido")
    @NotBlank(message = "Campo não pode ser vazio")
    private String email;

    @Schema(description = "Data no formato dia, mês e ano", example = "01/01/2000")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo não pode ser vazio")
    private LocalDate dataNascimento;

    @Schema(example = "M, F, OUTRO")
    @NotNull(message = "Campo não pode ser vazio")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Size(min = 3, message = "O nome não pode ter menos que 3 letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomeMae;

    @Size(min = 3, message = "O nome não pode ter menos que 3 letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomePai;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo não pode ser vazio")
    private SituacaoMatricula situacaoMatricula;

    @NotBlank(message = "Campo inválido")
    private String curso;
}
