package com.pedro_augusto.sistema_escolar.dtos;

import lombok.Data;

@Data
public class EnderecoListagemDTO {

    private Long id;
    private String cep;
    private String cidade;
    private String bairro;
    private Long alunoId;
}
