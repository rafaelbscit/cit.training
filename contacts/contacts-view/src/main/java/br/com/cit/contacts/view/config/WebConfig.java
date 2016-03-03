package br.com.cit.contacts.view.config;

import br.com.cit.contacts.view.config.mdc.MDCWebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MDCWebFilter mdcWebFilter() {
        return new MDCWebFilter();
    }

    @Bean
    public ViewResolver viewResolver() {
        return new ViewResolver();
    }

}
