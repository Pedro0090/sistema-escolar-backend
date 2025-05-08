package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.ProfessorComponent;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.ProfessorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class ProfessorService {

    private final ProfessorComponent professorComponent;
    private final ProfessorMapper professorMapper;

    @Autowired
    public ProfessorService(ProfessorComponent professorComponent, ProfessorMapper professorMapper) {
        this.professorComponent = professorComponent;
        this.professorMapper = professorMapper;
    }

    public List<ProfessorEntity> listAll() {
        return professorComponent.findAll();
    }

    public ProfessorPutRequestAndDetailsDTO findByMatricula(String matricula) {
       ProfessorEntity professorEntity = professorComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Professor não encontrado"));
       return professorMapper.toProfessorPutRequestAndDetailsDTO(professorEntity);
    }

    public ProfessorPostRequestDTO save(ProfessorPostRequestDTO professorPostRequestDTO) {
        ProfessorEntity professorEntity = professorMapper.toProfessor(professorPostRequestDTO);
        ProfessorEntity professorEntitySalvo =
                professorComponent.adicionarMatriculaAndSalvar(professorEntity, gerarMatriculaProfessor());
        return professorMapper.toProfessorRequestDTO(professorEntitySalvo);
    }

    public ProfessorPutRequestAndDetailsDTO replace(ProfessorPutRequestAndDetailsDTO professorPutRequestAndDetailsDTO) {
        professorComponent.findByMatricula(professorPutRequestAndDetailsDTO.getMatricula());
        ProfessorEntity professorAtualizado =
                professorComponent.salvar(professorMapper.toProfessor(professorPutRequestAndDetailsDTO));
        return professorMapper.toProfessorPutRequestAndDetailsDTO(professorAtualizado);
    }

    public void delete(String matricula) {
        professorComponent.deletar(matricula);
    }


    private String gerarMatriculaProfessor() {
        StringBuilder matricula;
        do {
            matricula = new StringBuilder("SAP");
            LocalDate data = LocalDate.now();
            matricula.append(data.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            long numero = new Random().nextLong(1, 999999);
            matricula.append(String.format("%06d", numero));
        } while (professorComponent.findByMatricula(matricula.toString()).isPresent());
        return  matricula.toString();
    }
}
