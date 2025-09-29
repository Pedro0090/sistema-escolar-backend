package com.pedro_augusto.sistema_escolar.repository;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplinaEntity, Long> {

    Optional<AlunoDisciplinaEntity> findByAlunoEntityIdAndDisciplinaEntityId(Long alunoId, Long dsciplinaId);
}
