package com.pedro_augusto.sistema_escolar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoListagemDTO {

    private Long id;
    private String cep;
    private String cidade;
    private String bairro;
    private Long alunoId;
}
