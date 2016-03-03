package br.com.cit.contacts.view.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan({"br.com.cit.contacts.api.config",
        "br.com.cit.contacts.view.config",
        "br.com.cit.contacts.repository.config"})
public class AppConfig {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AppConfig.class, args);
    }
}
