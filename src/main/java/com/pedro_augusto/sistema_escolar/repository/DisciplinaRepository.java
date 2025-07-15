package com.pedro_augusto.sistema_escolar.repository;

import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
}
