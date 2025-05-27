package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfessorComponent {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorComponent(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorEntity> findAll() {
        return professorRepository.findAll();
    }

    public Optional<ProfessorEntity> findByMatricula(String matricula) {
        return professorRepository.findByMatricula(matricula);
    }

    public ProfessorEntity adicionarMatriculaAndSalvar(ProfessorEntity professorEntity, String matricula) {
        professorEntity.setMatricula(matricula);
        return salvar(professorEntity);
    }

    public ProfessorEntity salvar(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

    public void deletar(String matricula) {
        ProfessorEntity professorEntitySalvo = findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Professor não encontrado"));
        professorRepository.delete(professorEntitySalvo);
    }
}
