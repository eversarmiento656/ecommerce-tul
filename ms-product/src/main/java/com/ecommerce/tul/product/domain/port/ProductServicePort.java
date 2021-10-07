package com.ecommerce.tul.product.domain.port;

import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.product.domain.data.Product;

public interface ProductServicePort {
	List<Product> getProducts();
	boolean createProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(UUID id);
}
