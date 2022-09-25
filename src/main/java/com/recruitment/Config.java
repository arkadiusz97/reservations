package com.recruitment;

import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;


@Configuration
@PropertySource("file:application.properties")
@EntityScan("com.articles.Article")
public class Config implements
WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	@Value("${database.username}")
	String username;
	
	@Value("${database.password}")
	String password;
	
	@Value("${database.url}")
	String url;
	
	@Value("${database.dialect}")
	String dialect;
	
	@Value("${server.port}")
	String port;
	
	@Bean
	public org.hibernate.cfg.Configuration getConfig() {
		org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();

		config.setProperty("hibernate.connection.url", url);
		config.setProperty("hibernate.connection.username", username);
		config.setProperty("hibernate.connection.password", password);
		config.setProperty("hibernate.connection.hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		config.setProperty("hibernate.dialect", dialect);
		config.addAnnotatedClass(ReservationWithDetails.class);
		config.addAnnotatedClass(Reservation.class);

		return config;
	}
	
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setPort(Integer.parseInt(port));
	}
}
