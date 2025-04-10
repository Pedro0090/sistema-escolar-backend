package com.pedro_augusto.sistema_escolar.component;

import com.pedro_augusto.sistema_escolar.domain.Aluno;
import com.pedro_augusto.sistema_escolar.exceptions.BadRequestException;
import com.pedro_augusto.sistema_escolar.repository.AlunoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlunoComponent {

    private final AlunoRespository alunoRespository;

    public List<Aluno> findAll() {
        return alunoRespository.findAll();
    }

    public Aluno findById(Long id) {
        return alunoRespository.findById(id).orElseThrow(() -> new BadRequestException("Aluno not found"));
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRespository.save(aluno);
    }

    public void deletar(Long id) {
        alunoRespository.delete(findById(id));
    }

    public Aluno findMatricula(Integer matricula) {
        return Optional.ofNullable(alunoRespository.findByMatricula(matricula))
                .orElseThrow(() -> new BadRequestException("Aluno not found"));
    }
}
