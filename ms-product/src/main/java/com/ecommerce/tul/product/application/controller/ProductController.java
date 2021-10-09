package com.ecommerce.tul.product.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.tul.product.domain.data.Product;
import com.ecommerce.tul.product.domain.port.ProductServicePort;

@RestController
@RequestMapping("v1")
public class ProductController {

	@Autowired
	private ProductServicePort productService;

	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@PutMapping("/product")
	public void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
	}

	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable UUID id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable UUID id) {
		return productService.getProductById(id);
	}

	@PutMapping("/products")
	public void updateProducts(@RequestBody List<Product> product) {
		productService.updateProducts(product);
	}
}
