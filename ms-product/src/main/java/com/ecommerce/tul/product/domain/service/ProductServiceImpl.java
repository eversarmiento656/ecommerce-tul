package com.ecommerce.tul.product.domain.service;

import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.product.domain.data.Product;
import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.domain.port.ProductServicePort;

public class ProductServiceImpl implements ProductServicePort {

	private ProductPersistencePort productPersistencePort;

	public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
		this.productPersistencePort = productPersistencePort;
	}

	@Override
	public List<Product> getProducts() {
		return this.productPersistencePort.getProducts();
	}

	@Override
	public boolean createProduct(Product product) {
		return this.productPersistencePort.createProduct(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		return this.productPersistencePort.updateProduct(product);
	}

	@Override
	public boolean deleteProduct(UUID id) {
		return this.productPersistencePort.deleteProduct(id);
	}

}
