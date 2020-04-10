package com.example.demo2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.PathMatcher
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    @Override
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.setPathMatcher(versionedAntPathMatcher())
    }

    @Bean
    fun versionedAntPathMatcher(): PathMatcher = VersionedAntPathMatcher()
}