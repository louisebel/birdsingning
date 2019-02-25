package com.birdsinging;

import com.birdsinging.level.LevelRepository;
import com.birdsinging.level.LevelRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public LevelRepository levelRepository(){
        return new LevelRepositoryImpl();
    }

}
