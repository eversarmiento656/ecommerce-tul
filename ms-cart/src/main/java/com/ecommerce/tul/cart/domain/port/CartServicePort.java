package com.ecommerce.tul.cart.domain.port;

import com.ecommerce.tul.cart.domain.data.Cart;
import com.ecommerce.tul.cart.domain.data.Checkout;
import com.ecommerce.tul.cart.domain.data.ProductDetail;

public interface CartServicePort {

	Cart getCartById(String id);

	Cart addProductCart(String id, ProductDetail product);

	void deleteProductCart(String id, String idProduct);

	void deleteCart(String id);

	void updateProductCart(String idCart, ProductDetail product);

	Checkout checkout(String idCart);

}
