package com.SpringJwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class SpringJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.SpringJwt"))
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo(){
		return new ApiInfo(
				"Institution Api",
				"Sample API for Course and Instructor Information",
				"1.0",
				"Free ApI to use",
				new springfox.documentation.service.Contact("KiranVulchakoti","http://kiran.io","kiran@kv.com"),
				"Api Licence",
				"http://kiran.io",
				Collections.emptyList());
	}
}