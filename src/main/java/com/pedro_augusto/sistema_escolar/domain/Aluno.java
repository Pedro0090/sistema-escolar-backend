package com.pedro_augusto.sistema_escolar.domain;

import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@Data
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUNO")
    private Long id;

    @Column(name= "NOME", nullable = false)
    @Pattern(regexp = "^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nome;

    @Column(name= "CPF", nullable = false, unique = true)
    @NotBlank(message = "Campo não pode ser vazio")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(name= "MATRICULA", nullable = false, unique = true)
    @Min(100000)
    @Max(999999)
    @NotNull(message = "Campo não pode ser vazio")
    private Integer matricula;

    @Column(name= "TELEFONE", nullable = false)
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
    message = "Telefone inválido! Use o formato (99) 99999-9999")
    @NotEmpty(message = "Campo não pode ser vazio")
    private String telefone;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email(message = "Email inválido")
    @NotBlank(message = "Campo não pode ser vazio")
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    @NotNull(message = "Campo não pode ser vazio")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO", nullable = false)
    @NotNull(message = "Campo não pode ser vazio")
    private Sexo sexo;

    @Column(name= "NOME_MAE", nullable = false)
    @Pattern(regexp = "^^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomeMae;

    @Column(name= "NOME_PAI", nullable = false)
    @Pattern(regexp = "^[A-ZÀ-Ö][a-zà-ö]+( (?:[dD]e|[dD]o|[dD]a|[dD]os|[dD]as|[A-ZÀ-Ö][a-zà-ö]+))*$",
            message = "Campo deve iniciar com letra maiúscula e ter somente letras")
    @NotBlank(message = "Campo não pode ser vazio")
    private String nomePai;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_MATRICULA")
    @NotNull(message = "Campo não pode ser vazio")
    private SituacaoMatricula situacaoMatricula;

    @Column(name = "CURSO")
    @NotBlank(message = "Campo inválido")
    private String curso;

    public void setNome(String nome) {
        this.nome = nome != null ? nome.trim() : null;
    }
}
