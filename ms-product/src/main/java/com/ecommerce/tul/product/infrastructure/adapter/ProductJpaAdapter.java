package com.ecommerce.tul.product.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.tul.product.domain.data.Product;
import com.ecommerce.tul.product.domain.exception.BussinessException;
import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.infrastructure.entity.ProductEntity;
import com.ecommerce.tul.product.infrastructure.repository.ProductRepository;

public class ProductJpaAdapter implements ProductPersistencePort {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		List<Product> listProduct = new ArrayList<>();
		this.productRepository.findAll()
				.forEach(x -> listProduct.add(new Product(x.getSku(), x.getName(), x.getDescription(), x.getPrice())));
		return listProduct;
	}

	@Override
	@Transactional
	public boolean createProduct(Product product) {
		try {
			this.productRepository.save(new ProductEntity(product.getSku(), product.getName(), product.getDescription(),
					product.getPrice()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateProduct(Product product) {
		try {
			ProductEntity entity = this.productRepository.findById(product.getSku())
					.orElseThrow(() -> new BussinessException("id no encontrado"));

			entity.setDescription(product.getDescription());
			entity.setName(product.getName());
			entity.setPrice(product.getPrice());
			this.productRepository.save(entity);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteProduct(UUID id) {
		try {
			ProductEntity entity = this.productRepository.findById(id)
					.orElseThrow(() -> new BussinessException("id no encontrado"));

			this.productRepository.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
