package com.pedro_augusto.sistema_escolar.repository;

import com.pedro_augusto.sistema_escolar.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRespository extends JpaRepository<Aluno, Long> {

    public Aluno findByMatricula(Integer matricula);
}
