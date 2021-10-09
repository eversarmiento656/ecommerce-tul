package com.ecommerce.tul.cart.infrastructure.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.tul.cart.domain.data.Product;
import com.ecommerce.tul.cart.domain.port.CartRestPort;

public class CartRestTemplateAdapter implements CartRestPort {

	@Value("${endpoint.service.product}")
	private String host;
	private static final String PRODUCT_BY_ID = "product/";
	private static final String PRODUCTS = "products";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Product getProduct(String id) {
		return this.restTemplate.getForObject(host + PRODUCT_BY_ID + id, Product.class);
	}

	@Override
	public void updateListProduct(List<Product> listProduct) {
		this.restTemplate.put(host + PRODUCTS, listProduct);
	}
}
