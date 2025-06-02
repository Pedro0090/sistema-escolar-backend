package com.pedro_augusto.sistema_escolar.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class GeradorMatricula {

    public static String gerarMatricula(TipoMatricula tipoMatricula) {
        StringBuilder matricula = tipoMatricula == TipoMatricula.ALUNO ? new StringBuilder("SAA") : new StringBuilder("SAP");
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        matricula.append(data).append(codigo);
        return matricula.toString();
    }
}
