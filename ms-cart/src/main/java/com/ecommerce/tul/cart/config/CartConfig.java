package com.ecommerce.tul.cart.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.tul.cart.domain.port.CartPersistencePort;
import com.ecommerce.tul.cart.domain.port.CartRestPort;
import com.ecommerce.tul.cart.domain.port.CartServicePort;
import com.ecommerce.tul.cart.domain.service.CartServiceImpl;
import com.ecommerce.tul.cart.infrastructure.adapter.CartRedisAdapter;
import com.ecommerce.tul.cart.infrastructure.adapter.CartRestTemplateAdapter;

@Configuration
public class CartConfig {
	@Bean
	public CartPersistencePort cartPersistence() {
		return new CartRedisAdapter();
	}

	@Bean
	public CartServicePort cartService() {
		return new CartServiceImpl(cartPersistence(), cartRestPort());
	}

	@Bean
	public CartRestPort cartRestPort() {
		return new CartRestTemplateAdapter();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMillis(3000)).setReadTimeout(Duration.ofMillis(3000)).build();
	}
}
