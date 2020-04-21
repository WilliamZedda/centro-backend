package com.tecnositaf.centrobackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration

@PropertySources({ @PropertySource("classpath:application.properties"),
		@PropertySource(value = "classpath:/${env}/database.properties", ignoreResourceNotFound = false),
		@PropertySource(value = "classpath:/${env}/filesystem.properties", ignoreResourceNotFound = false),
		@PropertySource(value = "classpath:/${env}/endpoint.properties", ignoreResourceNotFound = false), })

@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Value("${env}")
	String env;

	@Value("${webSocketUrl}")
	String webSocketUrl;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins(webSocketUrl).withSockJS();
	}

}
