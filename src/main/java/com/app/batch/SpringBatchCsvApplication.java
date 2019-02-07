package com.app.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile(value = {"batch"})
public class SpringBatchCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchCsvApplication.class, args);
	}
}
