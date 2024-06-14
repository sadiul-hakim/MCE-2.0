package org.learn.cms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
