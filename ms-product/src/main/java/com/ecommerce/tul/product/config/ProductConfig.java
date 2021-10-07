package com.ecommerce.tul.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.domain.port.ProductServicePort;
import com.ecommerce.tul.product.domain.service.ProductServiceImpl;
import com.ecommerce.tul.product.infrastructure.adapter.ProductJpaAdapter;

@Configuration
public class ProductConfig {
	@Bean
	public ProductPersistencePort productPersistence() {
		return new ProductJpaAdapter();
	}

	@Bean
	public ProductServicePort productService() {
		return new ProductServiceImpl(productPersistence());
	}
}
