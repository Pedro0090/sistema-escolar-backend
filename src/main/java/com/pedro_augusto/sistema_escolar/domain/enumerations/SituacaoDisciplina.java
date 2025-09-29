package com.pedro_augusto.sistema_escolar.domain.enumerations;

public enum SituacaoDisciplina {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PLANEJAMENTO("Planejamento");

    public String descricao;

    SituacaoDisciplina(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
