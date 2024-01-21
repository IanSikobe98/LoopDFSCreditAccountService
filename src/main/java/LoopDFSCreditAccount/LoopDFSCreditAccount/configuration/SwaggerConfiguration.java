package LoopDFSCreditAccount.LoopDFSCreditAccount.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author babaliam
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public Docket api() {
        boolean swaggerswitch = environment.getRequiredProperty("swagger.enable") != null
                && environment.getRequiredProperty("swagger.enable").toString().equals("true");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("LoopDFSCreditAccount.LoopDFSCreditAccount.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .apiInfo(apiInfo()).enable(swaggerswitch);

    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "LoopDFS Credit-Account microservice API",
                "LoopDFS Credit-Account microservice rest API.",
                "v1.0.0",
                "Terms of service",
                "ianwalter309@gmail.com",
                "License of API",
                "#");
        return apiInfo;
    }
}
