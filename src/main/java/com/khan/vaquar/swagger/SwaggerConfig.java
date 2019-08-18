package com.khan.vaquar.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2WebFlux
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Webflux mongodb poc public-api")
				.apiInfo(apiInfo())
				.select()
				.apis( RequestHandlerSelectors.basePackage( "com.khan.vaquar" ) )
				.paths(paths())
				.build();
		
		
		 
	}
	/*
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }
	 */
	private Predicate<String> paths() {
		return or(regex("/.*"), regex("/.*"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("public-api")
				.description("public-api app API reference for developers")
				.termsOfServiceUrl("XXX-YYY-ZZZ.com").contact("info@vaquarkhan.com")
				.license("vaquarkhan License").licenseUrl("Licence@vaquarkhan.com").version("1.0").build();
	}

   

    
}
