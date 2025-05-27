package com.pedro_augusto.sistema_escolar.repository;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRespository extends JpaRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByMatricula(String matricula);
}
