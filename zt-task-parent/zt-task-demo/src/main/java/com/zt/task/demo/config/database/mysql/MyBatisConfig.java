package com.zt.task.demo.config.database.mysql;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    
@EnableAutoConfiguration  
public class MyBatisConfig {  
    @Bean    
    @ConfigurationProperties(prefix="mybatis.configuration")    
    public org.apache.ibatis.session.Configuration getMyBatisRedis(){    
    	org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();  
        return config;    
    }    
        
}