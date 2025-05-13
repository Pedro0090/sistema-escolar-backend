package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.ProfessorComponent;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorListagemDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPostRequestDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorPutRequestAndDetailsDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.ProfessorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class ProfessorService {

    private final ProfessorComponent professorComponent;
    private final ProfessorMapper professorMapper;

    @Autowired
    public ProfessorService(ProfessorComponent professorComponent, ProfessorMapper professorMapper) {
        this.professorComponent = professorComponent;
        this.professorMapper = professorMapper;
    }

    public List<ProfessorListagemDTO> listAll() {
        log.info("Buscando professores no banco de dados");
        List<ProfessorListagemDTO> listaProfessores = professorMapper.toListProfessorListagemDTO(professorComponent.findAll());
        log.info("{} professores encontrados", listaProfessores.size());
        return listaProfessores;
    }

    public ProfessorPutRequestAndDetailsDTO findByMatricula(String matricula) {
        log.info("Buscando professor com matricula {}", matricula);
       ProfessorEntity professorEntity = professorComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Professor não encontrado"));
        log.info("Professor com matricula {} encontrado", matricula);
       return professorMapper.toProfessorPutRequestAndDetailsDTO(professorEntity);
    }

    public ProfessorPostRequestDTO save(ProfessorPostRequestDTO professorPostRequestDTO) {
        log.info("Criando professor {} no banco de dados", professorPostRequestDTO.getNome());
        ProfessorEntity professorEntity = professorMapper.toProfessor(professorPostRequestDTO);
        ProfessorEntity professorEntitySalvo =
                professorComponent.adicionarMatriculaAndSalvar(professorEntity, gerarMatriculaProfessor());
        log.info("Professor {} salvo no banco de dados", professorEntitySalvo.getNome());
        return professorMapper.toProfessorRequestDTO(professorEntitySalvo);
    }

    public ProfessorPutRequestAndDetailsDTO replace(ProfessorPutRequestAndDetailsDTO professorPutRequestAndDetailsDTO) {
        log.info("Buscando professor com matricula {} no banco de dados", professorPutRequestAndDetailsDTO
                .getMatricula());
        professorComponent.findByMatricula(professorPutRequestAndDetailsDTO.getMatricula());
        ProfessorEntity professorAtualizado =
                professorComponent.salvar(professorMapper.toProfessor(professorPutRequestAndDetailsDTO));
        log.info("Professor com matricula {} atualizado", professorAtualizado.getMatricula());
        return professorMapper.toProfessorPutRequestAndDetailsDTO(professorAtualizado);
    }

    public void delete(String matricula) {
        log.info("Deletando professor com matricula {}", matricula);
        professorComponent.deletar(matricula);
        log.info("Professor com matricula {} deletado", matricula);
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
