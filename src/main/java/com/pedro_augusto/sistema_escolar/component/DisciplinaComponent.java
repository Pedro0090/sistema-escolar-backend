package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DisciplinaComponent {

    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaComponent(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<DisciplinaEntity> findAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<DisciplinaEntity> findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    public DisciplinaEntity salvar(DisciplinaEntity disciplinaEntity) {
        return disciplinaRepository.save(disciplinaEntity);
    }

    public void deletar(Long id) {
        DisciplinaEntity disciplina = findById(id).orElseThrow(() -> new BadRequestException("Disciplina não encontrada"));
        disciplinaRepository.delete(disciplina);
    }
}
