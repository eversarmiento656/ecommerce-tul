package com.ecommerce.tul.product.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.tul.product.domain.data.Product;
import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.domain.util.BussinessException;
import com.ecommerce.tul.product.domain.util.Constant;
import com.ecommerce.tul.product.infrastructure.entity.ProductEntity;
import com.ecommerce.tul.product.infrastructure.repository.ProductRepository;

public class ProductJpaAdapter implements ProductPersistencePort {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		List<Product> listProduct = new ArrayList<>();
		this.productRepository.findAll().forEach(pro -> listProduct.add(new Product(pro.getSku(), pro.getName(),
				pro.getDescription(), pro.getPrice(), pro.isDiscount(), pro.getQuantity())));
		return listProduct;
	}

	@Override
	public Product createProduct(Product product) {
		ProductEntity entidad = this.productRepository.save(new ProductEntity(product.getSku(), product.getName(),
				product.getDescription(), product.getPrice(), product.isDiscount(), product.getQuantity()));
		product.setSku(entidad.getSku());
		return product;

	}

	@Override
	public void updateProduct(Product product) {
		ProductEntity entity = getProduct(product.getSku());
		entity.setDescription(product.getDescription());
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
		entity.setQuantity(product.getQuantity());
		entity.setDiscount(product.isDiscount());
		this.productRepository.save(entity);
	}

	@Override
	public void deleteProduct(UUID id) {
		ProductEntity entity = getProduct(id);
		this.productRepository.delete(entity);
	}

	@Override
	public Product getProductById(UUID id) {
		ProductEntity entity = getProduct(id);
		return new Product(entity.getSku(), entity.getName(), entity.getDescription(), entity.getPrice(),
				entity.isDiscount(), entity.getQuantity());
	}

	private ProductEntity getProduct(UUID id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new BussinessException(String.format(Constant.ID_NOT_FOUND, id.toString())));
	}

	@Override
	public void updateListProduct(List<Product> listProduct) {
		listProduct.forEach(this::updateProduct);
	}
}
