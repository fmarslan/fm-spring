/**
 * 
 * MIT License
 * 
 * Copyright (c) 2017 Think Wong
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

package com.fmarslan.spring.base.application.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 properties.
 *
 * @author Think Wong
 *
 * edited by fmarslan
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(Swagger2Properties.class)
public class SwaggerConfiguration implements WebMvcConfigurer {


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket apiDocket(Swagger2Properties swagger2Properties) {

    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(swagger2Properties)).select()
        .paths(paths(swagger2Properties)).apis(apis(swagger2Properties)).build()
        .globalOperationParameters(globalOperationParameters(swagger2Properties));
  }

  private Predicate<RequestHandler> apis(Swagger2Properties swagger2Properties) {

    List<Predicate<RequestHandler>> basePackages = new LinkedList<>();

    if (swagger2Properties.getBasePackage().isEmpty()) {
      basePackages.add(RequestHandlerSelectors.any());
    }
    for (String basePackage : swagger2Properties.getBasePackage()) {
      basePackages.add(RequestHandlerSelectors.basePackage(basePackage));
    }

    return Predicates.or(basePackages);
  }

  private Predicate<String> paths(Swagger2Properties swagger2Properties) {

    List<Predicate<String>> basePaths = new ArrayList<>();

    if (swagger2Properties.getBasePath().isEmpty()) {
      basePaths.add(PathSelectors.any());
    }
    for (String basePath : swagger2Properties.getBasePath()) {
      basePaths.add(PathSelectors.ant(basePath));
    }

    List<Predicate<String>> excludePaths = new ArrayList<>();
    for (String excludePath : swagger2Properties.getExcludePath()) {
      excludePaths.add(PathSelectors.ant(excludePath));
    }

    return Predicates.and(Predicates.not(Predicates.or(excludePaths)), Predicates.or(basePaths));
  }

  private ApiInfo apiInfo(Swagger2Properties swagger2Properties) {
    return new ApiInfoBuilder().title(swagger2Properties.getApiInfo().getTitle())
        .description(swagger2Properties.getApiInfo().getDescription())
        .termsOfServiceUrl(swagger2Properties.getApiInfo().getTermsOfServiceUrl())
        .contact(new Contact(swagger2Properties.getApiInfo().getContact().getName(),
            swagger2Properties.getApiInfo().getContact().getUrl(),
            swagger2Properties.getApiInfo().getContact().getEmail()))
        .license(swagger2Properties.getApiInfo().getLicense())
        .licenseUrl(swagger2Properties.getApiInfo().getLicenseUrl())
        .version(swagger2Properties.getApiInfo().getVersion()).build();
  }

  private List<Parameter> globalOperationParameters(Swagger2Properties swagger2Properties) {
    List<Parameter> globalOperationParameters = new ArrayList<>();

    for (Swagger2Properties.Parameter parameter : swagger2Properties
        .getGlobalOperationParameter()) {
      Parameter globalOperationParameter = new ParameterBuilder().name(parameter.getName())
          .description(parameter.getDescription()).modelRef(new ModelRef(parameter.getModelRef()))
          .parameterType(parameter.getParamType()).required(parameter.getRequired()).build();
      globalOperationParameters.add(globalOperationParameter);
    }
    return globalOperationParameters;
  }

}
