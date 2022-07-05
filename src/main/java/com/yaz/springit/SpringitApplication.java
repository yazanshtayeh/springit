package com.yaz.springit;

import lombok.extern.flogger.Flogger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
public class SpringitApplication {

	private static final Logger log=  LoggerFactory.getLogger(SpringitApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);

	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			log.error("CommandLineRunner.run();");
			log.warn("CommandLineRunner.run();");
			log.info("CommandLineRunner.run();");
			log.trace("CommandLineRunner.run();");
			log.debug("CommandLineRunner.run();");

		};
	}
}
