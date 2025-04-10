package com.pedro_augusto.sistema_escolar.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AlunoPutRequestBody {

    @Id
    private Long id;

    @Pattern(regexp = "^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nome;

    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Telefone inválido! Use o formato (99) 99999-9999")
    @NotEmpty(message = "Campo não pode ser vazio")
    private String telefone;

    @Email(message = "Email inválido")
    @NotBlank(message = "Campo não pode ser vazio")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo não pode ser vazio")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo não pode ser vazio")
    private Sexo sexo;

    @Pattern(regexp = "^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomeMae;

    @Pattern(regexp = "^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomePai;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo não pode ser vazio")
    private SituacaoMatricula situacaoMatricula;

    @NotBlank(message = "Campo inválido")
    private String curso;
}
