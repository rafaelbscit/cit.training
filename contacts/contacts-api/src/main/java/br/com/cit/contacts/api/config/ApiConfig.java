package br.com.cit.contacts.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"br.com.cit.contacts.api.controller",
        "br.com.cit.contacts.api.service"})
public class ApiConfig {
}
