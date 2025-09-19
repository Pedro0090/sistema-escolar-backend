package com.pedro_augusto.sistema_escolar.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rel_alun_disc")
@Data
@NoArgsConstructor
public class AlunoDisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ALUNO")
    private AlunoEntity alunoEntity;

    @ManyToOne
    @JoinColumn(name = "ID_DISCIPLINA")
    private DisciplinaEntity disciplinaEntity;
}
