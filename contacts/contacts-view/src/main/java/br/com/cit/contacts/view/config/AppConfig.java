package br.com.cit.contacts.view.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.com.cit.contacts.api.config")
public class AppConfig {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AppConfig.class, args);
    }
}
