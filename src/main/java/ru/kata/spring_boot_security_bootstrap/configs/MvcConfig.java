package ru.kata.spring_boot_security_bootstrap.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("mainPage");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("mainPage");
        registry.addViewController("/").setViewName("login");
    }
}