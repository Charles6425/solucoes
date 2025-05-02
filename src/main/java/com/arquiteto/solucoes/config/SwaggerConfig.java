package com.arquiteto.solucoes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Lançamentos")
                        .description("Documentação da API para gerenciamento de lançamentos financeiros - Desafio Arquiteto de Soluções")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Charles Müller")
                                .email("charlesmullerti@gmail.com")
                                .url("https://www.exemplo.com")));
    }
}