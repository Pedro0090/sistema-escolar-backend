package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.AlunoDisciplinaEntity;
import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.domain.DisciplinaEntity;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.AlunoDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoDisciplinaComponent {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    public AlunoDisciplinaComponent(AlunoDisciplinaRepository alunoDisciplinaRepository) {
        this.alunoDisciplinaRepository = alunoDisciplinaRepository;
    }

    public List<AlunoDisciplinaEntity> findAll() {
        return alunoDisciplinaRepository.findAll();
    }

    public AlunoDisciplinaEntity findbyid(Long id) {
        return alunoDisciplinaRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Relacionamento não encontrado"));
    }

    public AlunoDisciplinaEntity findByIdAlunoAndIdDisciplina(Long idAluno, Long idDisciplina) {
        return alunoDisciplinaRepository.findByAlunoEntityIdAndDisciplinaEntityId(idAluno, idDisciplina).orElseThrow(
                () -> new BadRequestException("Relacionamento não encontrado"));
    }

    public AlunoDisciplinaEntity salvar(AlunoDisciplinaEntity alunoDisciplinaEntity) {
        return alunoDisciplinaRepository.save(alunoDisciplinaEntity);
    }

    public AlunoDisciplinaEntity setarAndSalvar(AlunoEntity alunoEntity, DisciplinaEntity disciplinaEntity) {
        AlunoDisciplinaEntity alunoDisciplinaEntity = new AlunoDisciplinaEntity();
        alunoDisciplinaEntity.setAlunoEntity(alunoEntity);
        alunoDisciplinaEntity.setDisciplinaEntity(disciplinaEntity);
        return salvar(alunoDisciplinaEntity);
    }

    public void deletar(AlunoDisciplinaEntity alunoDisciplinaEntity) {
        alunoDisciplinaRepository.delete(alunoDisciplinaEntity);
    }
}
