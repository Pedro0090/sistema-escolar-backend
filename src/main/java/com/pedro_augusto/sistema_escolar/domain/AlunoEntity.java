package com.pedro_augusto.sistema_escolar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro_augusto.sistema_escolar.domain.enumerations.Sexo;
import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoMatricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUNO")
    private Long id;

    @Column(name= "NOME", nullable = false)
    private String nome;

    @Column(name= "CPF", nullable = false, unique = true)
    private String cpf;

    @Column(name= "MATRICULA", nullable = false, unique = true)
    private String matricula;

    @Column(name= "TELEFONE", nullable = false)
    private String telefone;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO", nullable = false)
    private Sexo sexo;

    @Column(name= "NOME_MAE", nullable = false)
    private String nomeMae;

    @Column(name= "NOME_PAI", nullable = false)
    private String nomePai;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_MATRICULA")
    private SituacaoMatricula situacaoMatricula;

    @Column(name = "CURSO")
    private String curso;

    public void setNome(String nome) {
        this.nome = nome != null ? nome.trim() : null;
    }
}
