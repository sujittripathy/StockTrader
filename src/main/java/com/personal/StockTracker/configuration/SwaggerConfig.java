package com.personal.StockTracker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Stock Trade Application")
				.description("Stock trade application description")
				.contact(new Contact("Sujit", "","sujit.tripathy@gmail.com"))
				.build();
	}

	@Bean
	public Docket apiDetail(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.apiInfo(apiInfo()).select().build();
	}
}
