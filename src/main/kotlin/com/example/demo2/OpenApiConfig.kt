package com.example.demo2

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils


@Configuration
class OpenApiConfig {
    @Autowired
    private lateinit var buildProps: BuildProperties

    @Bean
    fun applicationOpenApi() = OpenAPI().info(getInfo()).externalDocs(getExternalDocs())

    private fun getInfo() = Info()
            .title("Demo Spring API versioning application with Swagger")
            .description(getDescription())
            .version(getVersion())

    private fun getDescription() = ResourceUtils.getFile("classpath:openapi/description").readText()
    private fun getVersion() = buildProps.version

    private fun getExternalDocs() = ExternalDocumentation()
            .description("GitHub repository")
            .url("https://github.com/mcdimus/demo-spring-api-versioning-and-swagger")
}