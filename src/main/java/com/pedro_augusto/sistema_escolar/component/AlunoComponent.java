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

    public AlunoEntity findById(Long id) {
        return alunoRespository.findById(id).orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
    }

    public AlunoEntity adicionarMatricula(AlunoEntity alunoEntity, String matricula) {
        alunoEntity.setMatricula(matricula);
        return alunoEntity;
    }

    public AlunoEntity salvar(AlunoEntity alunoEntity) {
        return alunoRespository.save(alunoEntity);
    }

    public void deletar(Long id) {
        alunoRespository.delete(findById(id));
    }

//    public AlunoEntity findByMatricula(String matricula) {
//        return Optional.ofNullable(alunoRespository.findByMatricula(matricula))
//                .orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
//    }
}
