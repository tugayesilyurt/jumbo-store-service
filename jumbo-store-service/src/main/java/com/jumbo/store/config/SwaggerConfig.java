package com.jumbo.store.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	String paths[] = { "/v1/**" };
	String packagesToscan[] = { "com.jumbo.store.controller", "com.jumbo.store.service" };

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("jumbo-store-service").pathsToMatch(paths).packagesToScan(packagesToscan).build();
	}

}
