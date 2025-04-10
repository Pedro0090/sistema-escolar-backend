package com.pedro_augusto.sistema_escolar.domain.enumerations;

public enum SituacaoMatricula {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PENDENTE("Pendente");

    private String descricao;

    SituacaoMatricula(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
