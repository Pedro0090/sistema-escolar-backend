package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.AlunoDisciplinaComponent;
import com.pedro_augusto.sistema_escolar.dtos.AlunoDisciplinaDTO;
import com.pedro_augusto.sistema_escolar.mapper.AlunoDisciplinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoDisciplinaService {

    private final AlunoDisciplinaComponent alunoDisciplinaComponent;
    private final AlunoDisciplinaMapper alunoDisciplinaMapper;

    @Autowired
    public AlunoDisciplinaService(AlunoDisciplinaComponent alunoDisciplinaComponent, AlunoDisciplinaMapper alunoDisciplinaMapper) {
        this.alunoDisciplinaComponent = alunoDisciplinaComponent;
        this.alunoDisciplinaMapper = alunoDisciplinaMapper;

    }

    public List<AlunoDisciplinaDTO> listAll() {
        return alunoDisciplinaMapper.toListAlunoDisciplinaDTO(alunoDisciplinaComponent.findAll());
    }

    public AlunoDisciplinaDTO findById(Long id) {
        return alunoDisciplinaMapper.toAlunoDisciplinaDTO(alunoDisciplinaComponent.findbyid(id));
    }
}
