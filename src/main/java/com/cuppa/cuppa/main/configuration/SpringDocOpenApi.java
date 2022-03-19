//package com.cuppa.cuppa.main.configuration;
//
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//
//public class SpringDocOpenApi {
//
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Cuppa Chat API")
//                                .description("Cuppa Chat API 문서입니다.")
//                                .version("v0.0.1")
//                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                                      .description("SpringShop Wiki Documentation")
//                                      .url("https://springshop.wiki.github.org/docs"));
//    }
//}
