package com.example.demo2

import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.Parameter
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils
import java.util.*


@Configuration
class OpenApiConfig {
    @Autowired
    private lateinit var buildProps: BuildProperties

    @Bean
    fun applicationOpenApi() = OpenAPI().info(getInfo()).externalDocs(getExternalDocs()).components(getComponents())

    private fun getInfo() = Info()
            .title("Demo Spring API versioning application with Swagger")
            .description(getDescription())
            .version(getVersion())

    private fun getDescription() = ResourceUtils.getFile("classpath:openapi/description").readText()
    private fun getVersion() = buildProps.version

    private fun getExternalDocs() = ExternalDocumentation()
            .description("GitHub repository")
            .url("https://github.com/mcdimus/demo-spring-api-versioning-and-swagger")

    private fun getComponents() = Components()
            .addParameters("header-x-application-id", getHeader("X-Application-Id"))
            .addParameters("header-x-user-id", getHeader("X-User-Id"))

    private fun getHeader(s: String) = Parameter().`in`(ParameterIn.HEADER.toString()).name(s).required(true).schema(StringSchema()._default("swagger-ui"))

    @Bean
    fun operationCustomizer() =
            OperationCustomizer { o, h ->
                val newParameters: MutableList<Parameter> = ArrayList()
                newParameters.add(Parameter().`$ref`("#/components/parameters/header-x-application-id"))
                newParameters.add(Parameter().`$ref`("#/components/parameters/header-x-user-id"))
                if (o.parameters != null) newParameters.addAll(o.parameters)
                o.parameters(newParameters)
            }
}