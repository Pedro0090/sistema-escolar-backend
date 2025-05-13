package com.pedro_augusto.sistema_escolar.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;


@OpenAPIDefinition(
        info = @Info(
                title = "Sistema Escolar Documentação",
                description = "Documentação do mini sistema escolar"
        ),
        tags = {@Tag(name = "Aluno", description = "Operações relacionadas ao Aluno"),
                @Tag(name = "Professor", description = "Operações relacionadas ao Professor")}
)
public class SpringDocConfiguration {
}
