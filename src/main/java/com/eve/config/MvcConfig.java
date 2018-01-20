package com.eve.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/user/registration").setViewName("registration");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/403").setViewName("errors/403");
        registry.addViewController("/home/events").setViewName("eventsPage");

    }
}
