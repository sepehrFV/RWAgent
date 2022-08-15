package com.tecnotree.rwagent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.tecnotree.rwagent"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "RWAgent APIs",
                "For TecnoTree challenge test ",
                "version 1.0.2",
                "Free to use",
                new Contact("Sepehr Fazeli","https://www.google.com/search?q=jewellery&rlz=1C1BNSD_enIR1018IR1018&sxsrf=ALiCzsY5ij5wxX8_k4yI5kpK45_tqymqPw%3A1660508156485&ei=_Ff5YtiQHbHd7_UP7dq8oAY&oq=jwe&gs_lcp=Cgdnd3Mtd2l6EAEYADIECAAQQzIHCAAQyQMQQzILCC4QgAQQxwEQrwEyBwguENQCEAoyBQgAEIAEMgQIABAKMgQILhAKMgcIABCABBAKMgUIABCABDIFCAAQgAQ6BwgjEOoCECc6BAgjECc6CAguENQCEJECOgUILhCABDoICC4QgAQQ1AI6CgguEMcBENEDEEM6BAguEENKBAhBGABKBAhGGABQAFifFWC4JWgBcAF4AIAB5gGIAZgFkgEDMi0zmAEAoAEBsAEKwAEB&sclient=gws-wiz","sepehr88258@gmail.com"),
                "sepehr88258@gmail.com",
                "http://localhost:8080/swagger-ui.html",
                Collections.emptyList());

    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }


}
