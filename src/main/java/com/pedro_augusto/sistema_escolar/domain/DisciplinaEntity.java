package com.pedro_augusto.sistema_escolar.domain;

import com.pedro_augusto.sistema_escolar.domain.enumerations.SituacaoDisciplina;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplina")
@NoArgsConstructor
@Getter
@Setter
public class DisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCIPLINA")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SITUACAO_DISCIPLINA")
    @Enumerated(EnumType.STRING)
    private SituacaoDisciplina situacaoDisciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROFESSOR")
    private ProfessorEntity professor;

    @OneToMany(mappedBy = "disciplinaEntity")
    private List<AlunoDisciplinaEntity> alunos = new ArrayList<>();
}
