package com.pedro_augusto.sistema_escolar.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@Data
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "RUA", nullable = false)
    private String rua;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ID_ALUNO")
    private AlunoEntity aluno;
}
