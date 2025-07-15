package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.DisciplinaComponent;
import com.pedro_augusto.sistema_escolar.component.ProfessorComponent;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.DisciplinaDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.DisciplinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaComponent disciplinaComponent;
    private final DisciplinaMapper disciplinaMapper;
    private final ProfessorComponent professorComponent;

    @Autowired
    public DisciplinaService(DisciplinaComponent disciplinaComponent, DisciplinaMapper disciplinaMapper, ProfessorComponent professorComponent) {
        this.disciplinaComponent = disciplinaComponent;
        this.disciplinaMapper = disciplinaMapper;
        this.professorComponent = professorComponent;
    }

    public List<DisciplinaDTO> listAll() {
        return disciplinaMapper.toListDisciplinaDTO(disciplinaComponent.findAll());
    }

    public DisciplinaDTO findById(Long id) {
        DisciplinaEntity disciplina = disciplinaComponent.findById(id).orElseThrow(
                () -> new BadRequestException("Disciplina não encontrada"));
        return disciplinaMapper.toDisciplinaDTO(disciplina);
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        ProfessorEntity professorSalvo = professorComponent.findById(disciplinaDTO.getProfessorId());
        DisciplinaEntity disciplinaEntity = disciplinaMapper.toDisciplinaEntity(disciplinaDTO, professorSalvo);
        DisciplinaEntity disciplinaSalva = disciplinaComponent.salvar(disciplinaEntity);
        return disciplinaMapper.toDisciplinaDTO(disciplinaEntity);
    }

    public DisciplinaDTO replace(DisciplinaDTO disciplinaDTO) {
        disciplinaComponent.findById(disciplinaDTO.getId());
        ProfessorEntity professorSalvo = professorComponent.findById(disciplinaDTO.getProfessorId());
        DisciplinaEntity disciplinaAtualizada = disciplinaComponent.salvar(
                disciplinaMapper.toDisciplinaEntity(disciplinaDTO, professorSalvo));
        return disciplinaMapper.toDisciplinaDTO(disciplinaAtualizada);
    }

    public void delete(Long id) {
       disciplinaComponent.deletar(id);
    }
}
