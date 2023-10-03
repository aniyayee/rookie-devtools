package com.rookie.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.rookie.common.exception.error.ErrorCode;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger config for open api.
 *
 * @author yayee
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class OpenApiConfig {

    /**
     * open api extension by knife4j.
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public OpenApiConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    /**
     * @return swagger config
     */
    @Bean
    public Docket openApi() {
        String groupName = "Test Group";
        return new Docket(DocumentationType.OAS_30)
            .groupName(groupName)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build()
            .globalRequestParameters(getGlobalRequestParameters())
            .globalResponses(HttpMethod.GET, getGlobalResponse())
            .extensions(openApiExtensionResolver.buildExtensions(groupName))
            .extensions(openApiExtensionResolver.buildSettingExtensions());
    }

    /**
     * @return global response code->description
     */
    private List<Response> getGlobalResponse() {
        return ErrorCode.HTTP_STATUS_ALL.stream().map(
                a -> new ResponseBuilder().code(String.valueOf(a.code())).description(a.message()).build())
            .collect(Collectors.toList());
    }

    /**
     * @return global request parameters
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
            .name("AppKey")
            .description("App Key")
            .required(false)
            .in(ParameterType.QUERY)
            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
            .required(false)
            .build());
        return parameters;
    }

    /**
     * @return api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("My API")
            .description("test api")
            .contact(new Contact("yayee", "http://yayee.com", "yayee@gmail.com"))
            .termsOfServiceUrl("http://xxxxxx.com/")
            .version("1.0")
            .build();
    }
}
