/*
package com.tecnotree.rwagent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.tecnotree.rwagent"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "RWAgent API",
                " a code challenge for me by Tecnotree",
                "1.0.0",
                "free to use",
                "Swagger",
                "Sepehr Fazeli",
                "sepehr88258@gmail.com"
        );

    }





}
*/
