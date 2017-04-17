package com.luke.doubancrawler;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.luke.doubancrawler")
public class Application {
	
	private static final String springFile="classpath*:META-INF/spring/crawler-*.xml";
	private static Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("start spring boot");
		SpringApplication application = new SpringApplication(Application.class,springFile);
		application.run();
	}
}
