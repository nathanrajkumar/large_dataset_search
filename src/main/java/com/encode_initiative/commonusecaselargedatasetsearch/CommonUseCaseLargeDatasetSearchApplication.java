package com.encode_initiative.commonusecaselargedatasetsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CommonUseCaseLargeDatasetSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonUseCaseLargeDatasetSearchApplication.class, args);
	}

}
