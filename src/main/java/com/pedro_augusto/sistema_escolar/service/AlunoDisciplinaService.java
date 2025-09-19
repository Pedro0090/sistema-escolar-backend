package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoComponent;
import com.pedro_augusto.sistema_escolar.component.AlunoDisciplinaComponent;
import com.pedro_augusto.sistema_escolar.component.DisciplinaComponent;
import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDisciplinaDTO;
import com.pedro_augusto.sistema_escolar.mapper.AlunoDisciplinaMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoDisciplinaService {

    private final AlunoDisciplinaComponent alunoDisciplinaComponent;
    private final AlunoDisciplinaMapper alunoDisciplinaMapper;
    private final AlunoComponent alunoComponent;
    private final DisciplinaComponent disciplinaComponent;

    @Autowired
    public AlunoDisciplinaService(AlunoDisciplinaComponent alunoDisciplinaComponent, AlunoDisciplinaMapper alunoDisciplinaMapper, AlunoComponent alunoComponent, DisciplinaComponent disciplinaComponent) {
        this.alunoDisciplinaComponent = alunoDisciplinaComponent;
        this.alunoDisciplinaMapper = alunoDisciplinaMapper;
        this.alunoComponent = alunoComponent;
        this.disciplinaComponent = disciplinaComponent;
    }

    public List<AlunoDisciplinaDTO> listAll() {
        return alunoDisciplinaMapper.toListAlunoDisciplinaDTO(alunoDisciplinaComponent.findAll());
    }

    public AlunoDisciplinaDTO findById(Long id) {
        return alunoDisciplinaMapper.toAlunoDisciplinaDTO(alunoDisciplinaComponent.findbyid(id));
    }

    public AlunoDisciplinaDTO save(AlunoDisciplinaDTO alunoDisciplinaDTO) {
        AlunoEntity aluno = alunoComponent.findById(alunoDisciplinaDTO.getAlunoId());
        DisciplinaEntity disciplina = disciplinaComponent.findById(alunoDisciplinaDTO.getDisciplinaId());
        AlunoDisciplinaEntity alunoDisciplinaEntity = alunoDisciplinaComponent.setarAndSalvar(aluno, disciplina);
        return alunoDisciplinaMapper.toAlunoDisciplinaDTO(alunoDisciplinaEntity);
    }

    public AlunoDisciplinaDTO replace(AlunoDisciplinaDTO alunoDisciplinaDTO) {
        AlunoEntity aluno = alunoComponent.findById(alunoDisciplinaDTO.getAlunoId());
        DisciplinaEntity disciplina = disciplinaComponent.findById(alunoDisciplinaDTO.getDisciplinaId());
        AlunoDisciplinaEntity alunoDisciplinaEntity = alunoDisciplinaComponent.setarAndSalvar(aluno, disciplina);
        return alunoDisciplinaMapper.toAlunoDisciplinaDTO(alunoDisciplinaEntity);
    }

    public void delete(Long id) {
        AlunoDisciplinaEntity alunoDisciplinaEntity = alunoDisciplinaComponent.findbyid(id);
        alunoDisciplinaComponent.deletar(alunoDisciplinaEntity);
    }
}
