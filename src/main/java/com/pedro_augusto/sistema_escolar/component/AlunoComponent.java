package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.AlunoEntity;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.AlunoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoComponent {

    private final AlunoRespository alunoRespository;

    @Autowired
    public AlunoComponent(AlunoRespository alunoRespository) {
        this.alunoRespository = alunoRespository;
    }

    public List<AlunoEntity> findAll() {
        return alunoRespository.findAll();
    }

    public Optional<AlunoEntity> findByMatricula(String matricula) {
        return (alunoRespository.findByMatricula(matricula));
    }

    public AlunoEntity adicionarMatriculaAndSalvar(AlunoEntity alunoEntity, String matricula) {
        alunoEntity.setMatricula(matricula);
        return salvar(alunoEntity);
    }

    public AlunoEntity salvar(AlunoEntity alunoEntity) {
        return alunoRespository.save(alunoEntity);
    }

    public void deletar(String matricula) {
        AlunoEntity aluno = findByMatricula(matricula)
                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
        alunoRespository.delete(aluno);
    }
}
