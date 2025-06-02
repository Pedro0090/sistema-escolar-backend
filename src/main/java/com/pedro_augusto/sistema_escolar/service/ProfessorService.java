package com.pedro_augusto.sistema_escolar.service;

import com.pedro_augusto.sistema_escolar.component.ProfessorComponent;
import com.pedro_augusto.sistema_escolar.domain.ProfessorEntity;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorDTO;
import com.pedro_augusto.sistema_escolar.dtos.ProfessorListagemDTO;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.mapper.ProfessorMapper;
import com.pedro_augusto.sistema_escolar.utils.GeradorMatricula;
import com.pedro_augusto.sistema_escolar.utils.TipoMatricula;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProfessorDTO findByMatricula(String matricula) {
        log.info("Buscando professor com matricula {}", matricula);
       ProfessorEntity professorEntity = professorComponent.findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Professor não encontrado"));
        log.info("Professor com matricula {} encontrado", matricula);
       return professorMapper.toProfessorDTO(professorEntity);
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        log.info("Criando professor {} no banco de dados", professorDTO.getNome());
        ProfessorEntity professorEntity = professorMapper.toProfessor(professorDTO);
        ProfessorEntity professorEntitySalvo =
                professorComponent.adicionarMatriculaAndSalvar(professorEntity, gerarMatriculaValidaProfessor());
        log.info("Professor {} salvo no banco de dados", professorEntitySalvo.getNome());
        return professorMapper.toProfessorDTO(professorEntitySalvo);
    }

    public ProfessorDTO replace(ProfessorDTO professorDTO) {
        log.info("Buscando professor com matricula {} no banco de dados", professorDTO
                .getMatricula());
        professorComponent.findByMatricula(professorDTO.getMatricula());
        ProfessorEntity professorAtualizado =
                professorComponent.salvar(professorMapper.toProfessor(professorDTO));
        log.info("Professor com matricula {} atualizado", professorAtualizado.getMatricula());
        return professorMapper.toProfessorDTO(professorAtualizado);
    }

    public void delete(String matricula) {
        log.info("Deletando professor com matricula {}", matricula);
        professorComponent.deletar(matricula);
        log.info("Professor com matricula {} deletado", matricula);
    }

    private String gerarMatriculaValidaProfessor() {
        String matricula;
        do {
            matricula = GeradorMatricula.gerarMatricula(TipoMatricula.PROFESSOR);
        } while (professorComponent.findByMatricula(matricula).isPresent());
        return matricula;
    }
}
