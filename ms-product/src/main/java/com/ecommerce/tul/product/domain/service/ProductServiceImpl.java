package com.ecommerce.tul.product.domain.service;

import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.ecommercecommons.exception.BussinessException;
import com.ecommerce.tul.ecommercecommons.exception.TechnicalException;
import com.ecommerce.tul.ecommercecommons.models.Product;
import com.ecommerce.tul.product.domain.port.ProductPersistencePort;
import com.ecommerce.tul.product.domain.port.ProductServicePort;
import com.ecommerce.tul.product.domain.util.Constant;

public class ProductServiceImpl implements ProductServicePort {

	private ProductPersistencePort productPersistencePort;

	public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
		this.productPersistencePort = productPersistencePort;
	}

	@Override
	public Product createProduct(Product product) {
		try {
			return this.productPersistencePort.createProduct(product);
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_CREATE_PRODUCT);
		}
	}

	@Override
	public void updateProduct(Product product) {
		try {
			this.productPersistencePort.updateProduct(product);
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_UPDATE_PRODUCT);
		}
	}

	@Override
	public void deleteProduct(UUID id) {
		try {
			this.productPersistencePort.deleteProduct(id);
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_DELETE_PRODUCT);
		}
	}

	@Override
	public Product getProductById(UUID id) {
		try {
			return this.applyDiscount(productPersistencePort.getProductById(id));
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_FIND_PRODUCT);
		}
	}

	@Override
	public List<Product> getProducts() {
		try {
			List<Product> listProduct = this.productPersistencePort.getProducts();
			listProduct.forEach(pro -> {
				pro = this.applyDiscount(pro);
			});
			return listProduct;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_LIST_PRODUCT);
		}
	}

	@Override
	public void updateProducts(List<Product> listProducts) {
		try {
			this.productPersistencePort.updateListProduct(listProducts);
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_UPDATE_LIST_PRODUCT);
		}
	}

	private Product applyDiscount(Product product) {

		if (product.isDiscount()) {
			product.setPrice(product.getPrice() / 2);
		}
		return product;
	}

}
