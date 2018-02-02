package com.interlink.configuration;

import com.interlink.calendar.service.DateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.interlink.deserialization")
public class SpringConfiguration {

    @Bean
    public DateService dateService() {
        return new DateService();
    }
}