package de.ahus1.tracing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alexander Schwartz 2016
 */
@Configuration
@ComponentScan(basePackages = {"de.ahus1.tracing"})
public class ApplicationConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
