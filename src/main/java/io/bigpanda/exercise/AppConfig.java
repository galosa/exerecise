package io.bigpanda.exercise;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Vertx vertx(){
		VertxOptions vertxOptions = new VertxOptions();
    	vertxOptions.setBlockedThreadCheckInterval(1000 * 60 * 30); //half an hour
    	Vertx vertx = Vertx.factory.vertx(vertxOptions);
    	return vertx;
	}
}
