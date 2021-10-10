package com.ecommerce.tul.product.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.tul.ecommercecommons.exception.BussinessException;
import com.ecommerce.tul.ecommercecommons.models.Product;
import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.domain.util.Constant;
import com.ecommerce.tul.product.infrastructure.entity.ProductEntity;
import com.ecommerce.tul.product.infrastructure.mapper.ProductMapper;
import com.ecommerce.tul.product.infrastructure.repository.ProductRepository;

public class ProductJpaAdapter implements ProductPersistencePort {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		List<Product> listProduct = new ArrayList<>();
		this.productRepository.findAll().forEach(pro -> listProduct.add(ProductMapper.INSTANCE.entityToProduct(pro)));
		return listProduct;
	}

	@Override
	public Product createProduct(Product product) {
		ProductEntity entidad = this.productRepository.save(ProductMapper.INSTANCE.productToEntity(product));
		product.setSku(entidad.getSku());
		return product;

	}

	@Override
	public void updateProduct(Product product) {
		getProduct(product.getSku());
		this.productRepository.save(ProductMapper.INSTANCE.productToEntity(product));
	}

	@Override
	public void deleteProduct(UUID id) {
		ProductEntity entity = getProduct(id);
		this.productRepository.delete(entity);
	}

	@Override
	public Product getProductById(UUID id) {
		return ProductMapper.INSTANCE.entityToProduct(getProduct(id));
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
