package com.stackroute.movieservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MovieServiceApplication {
	// private static final Logger logger= (Logger) LoggerFactory.getLogger(MovieServiceApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(MovieServiceApplication.class, args);
//		logger.info("Springboot application started in logger");
//		SpringApplication app=new SpringApplication(MovieServiceApplication.class);
//		app.run(args);
	}
}
