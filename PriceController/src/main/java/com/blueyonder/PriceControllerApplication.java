package com.blueyonder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceControllerApplication.class, args);
	}

}
