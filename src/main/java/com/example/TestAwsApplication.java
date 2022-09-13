package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages="com.example.config")
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.controller","com.example.dao","com.example.service","com.example.aop"})
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.example.entity"})
@EnableJpaRepositories("com.example.dao")
@EnableRetry
@EnableJpaAuditing
public class TestAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAwsApplication.class, args);
	}

}
