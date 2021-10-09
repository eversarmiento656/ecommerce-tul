package com.ecommerce.tul.cart.domain.port;

import com.ecommerce.tul.cart.domain.data.Cart;

public interface CartPersistencePort {
	Cart findById(String id);

	String addProductCart(Cart cart);
	
	boolean deleteCart(String id);

}
