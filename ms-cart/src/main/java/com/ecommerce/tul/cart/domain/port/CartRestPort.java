package com.ecommerce.tul.cart.domain.port;

import java.util.List;

import com.ecommerce.tul.cart.domain.data.Product;

public interface CartRestPort {
	Product getProduct(String id);
	void updateListProduct(List<Product> listProduct);
}
