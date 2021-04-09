package com.cognizant.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Slf4j
public class SpringFoxConfig {

	@Autowired
	Environment env;

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(env.getProperty("string.main.package")))
				.paths(PathSelectors.any())
				.build().apiInfo(apiDetails());
		log.debug("Docket{}:", docket);
		return docket;
	}

	private ApiInfo apiDetails() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title(env.getProperty("string.swagg.title"))
				.description(env.getProperty("string.swagg.desc"))
				.build();
		log.debug("API Info{}:", apiInfo);
		return apiInfo;
	}
}