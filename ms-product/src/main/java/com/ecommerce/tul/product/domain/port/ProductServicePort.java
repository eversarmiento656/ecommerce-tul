package com.ecommerce.tul.product.domain.port;

import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.product.domain.data.Product;

public interface ProductServicePort {
	List<Product> getProducts();

	Product createProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(UUID id);

	Product getProductById(UUID id);
	
	void updateProducts(List<Product> listProducts);
}
