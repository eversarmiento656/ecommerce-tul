package com.ecommerce.tul.product.domain.port;

import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.ecommercecommons.models.Product;


public interface ProductPersistencePort {

	List<Product> getProducts();

	Product createProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(UUID id);

	Product getProductById(UUID id);
	
	void updateListProduct(List<Product> listProduct);
}
