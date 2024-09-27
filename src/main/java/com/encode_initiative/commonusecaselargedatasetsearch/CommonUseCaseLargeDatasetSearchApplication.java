package com.encode_initiative.commonusecaselargedatasetsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.encode_initiative.commonusecaselargedatasetsearch.repository")
public class CommonUseCaseLargeDatasetSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonUseCaseLargeDatasetSearchApplication.class, args);
	}
}
