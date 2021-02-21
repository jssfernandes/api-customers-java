package br.com.customers.configs;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Autowired
    private Environment env;
    @Bean
    public Docket api(@Autowired TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.customers")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(env.getProperty("info.app.name", "API - Customers"))
                .description("API customers"+ "\nEnvironment: " + String.join(", ", env.getActiveProfiles()))
                .version(env.getProperty("info.app.version", env.getProperty("info.app.bootstrap.version", "0.0.1-SNAPSHOT")))
                .build();
    }
}
