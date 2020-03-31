package com.tecnositaf.centrobackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource("classpath:application.properties"),
		@PropertySource(value = "classpath:/${env}/database.properties", ignoreResourceNotFound = false),
		@PropertySource(value = "classpath:/${env}/filesystem.properties", ignoreResourceNotFound = false),
		@PropertySource(value = "classpath:/${env}/endpoint.properties", ignoreResourceNotFound = false), })

public class EnviromentConfiguration {
	@Value("${env}")
	String env;
}
