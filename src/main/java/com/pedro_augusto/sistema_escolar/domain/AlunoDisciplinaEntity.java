package com.pedro_augusto.sistema_escolar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rel_alun_disc")
@Getter
@Setter
@NoArgsConstructor
public class AlunoDisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ALUNO")
    private AlunoEntity alunoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DISCIPLINA")
    private DisciplinaEntity disciplinaEntity;
}
