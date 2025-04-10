package com.pedro_augusto.sistema_escolar.domain.enumerations;

public enum Sexo {

    M("Masculino"),
    F("Feminino"),
    OUTRO("Outro");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

