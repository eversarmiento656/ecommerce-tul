package com.ecommerce.tul.cart.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ecommerce.tul.cart.domain.data.Cart;
import com.ecommerce.tul.cart.domain.data.Checkout;
import com.ecommerce.tul.cart.domain.data.Product;
import com.ecommerce.tul.cart.domain.data.ProductDetail;
import com.ecommerce.tul.cart.domain.data.stateEnum;
import com.ecommerce.tul.cart.domain.port.CartPersistencePort;
import com.ecommerce.tul.cart.domain.port.CartRestPort;
import com.ecommerce.tul.cart.domain.port.CartServicePort;
import com.ecommerce.tul.cart.domain.util.BussinessException;
import com.ecommerce.tul.cart.domain.util.Constant;
import com.ecommerce.tul.cart.domain.util.TechnicalException;

public class CartServiceImpl implements CartServicePort {

	private CartPersistencePort cartPersistencePort;
	private CartRestPort cartRestPort;

	public CartServiceImpl(CartPersistencePort cartPersistencePort, CartRestPort cartRestPort) {
		this.cartPersistencePort = cartPersistencePort;
		this.cartRestPort = cartRestPort;
	}

	@Override
	public Cart getCartById(String id) {
		try {
			return this.cartPersistencePort.findById(id);
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_FIND_CART);
		}
	}

	@Override
	public Cart addProductCart(String id, ProductDetail product) {
		try {
			Product productGet = this.cartRestPort.getProduct(product.getSku().toString());
			if (productGet == null) {
				throw new BussinessException(String.format(Constant.ERROR_FIND_PRODUCT, product.getSku().toString()));
			}
			Cart cart = exectueLogicAddProductToCart(id, product, productGet);
			cart.setId(this.cartPersistencePort.addProductCart(cart));
			return cart;
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_ADD_CART);
		}
	}

	private Cart exectueLogicAddProductToCart(String id, ProductDetail product, Product productGet) {
		product.setPriceUnit(productGet.getPrice());
		product.setName(productGet.getName());
		Cart cart = this.cartPersistencePort.findById(id);

		if (cart == null) {
			cart = new Cart(UUID.randomUUID().toString());
		}
		if (!accumulateQuantity(product, cart)) {
			cart.getListProduct().add(product);
		}
		return cart;
	}

	private boolean accumulateQuantity(ProductDetail product, Cart cart) {

		for (ProductDetail pro : cart.getListProduct()) {
			if (pro.getSku().equals(product.getSku())) {
				pro.setChosenQuantity(pro.getChosenQuantity() + product.getChosenQuantity());
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteProductCart(String idCart, String idProduct) {

		try {
			Cart cart = this.cartPersistencePort.findById(idCart);
			if (cart != null && cart.getListProduct().removeIf(pro -> pro.getSku().toString().equals(idProduct))) {
				this.cartPersistencePort.addProductCart(cart);
			} else {
				throw new BussinessException(String.format(Constant.ERROR_FIND_PRODUCT, idProduct));
			}
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_DELETE_PRODUCT_TECHNICAL);
		}
	}

	@Override
	public void updateProductCart(String idCart, ProductDetail product) {
		try {
			Cart cart = this.cartPersistencePort.findById(idCart);
			updateProductInList(product, cart);
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_UPDATE_LIST_CART);
		}
	}

	private void updateProductInList(ProductDetail product, Cart cart) {
		for (ProductDetail pro : cart.getListProduct()) {
			if (pro.getSku().equals(product.getSku())) {
				pro.setChosenQuantity(product.getChosenQuantity());
				this.cartPersistencePort.addProductCart(cart);
				return;
			}
		}
		throw new BussinessException(String.format(Constant.ERROR_UPDATE_LIST_CART, product.getSku().toString()));
	}

	@Override
	public Checkout checkout(String idCart) {

		try {
			Cart cart = this.cartPersistencePort.findById(idCart);
			this.cartRestPort.updateListProduct(this.getListProductToUpdate(cart));
			updateStateToComplete(cart);
			return new Checkout(cart.getTotal());
		} catch (BussinessException e) {
			throw e;
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_CHECKOUT);
		}

	}

	private void updateStateToComplete(Cart cart) {
		cart.setState(stateEnum.COMPLETE.getState());
		this.cartPersistencePort.addProductCart(cart);
	}

	private List<Product> getListProductToUpdate(Cart cart) throws BussinessException {
		List<Product> arrayProduct = new ArrayList<>();
		for (ProductDetail pro : cart.getListProduct()) {
			Product productBD = this.cartRestPort.getProduct(pro.getSku().toString());
			validateQuantity(pro, productBD);
			productBD.setQuantity(productBD.getQuantity() - pro.getChosenQuantity());
			arrayProduct.add(productBD);
		}
		return arrayProduct;
	}

	private void validateQuantity(ProductDetail pro, Product productBD) {
		if (productBD.getQuantity() < pro.getChosenQuantity()) {
			throw new BussinessException(
					String.format(Constant.ERROR_QUANTITY, productBD.getName(), productBD.getQuantity()));
		}
	}

	@Override
	public void deleteCart(String idCart) {
		try {
			this.cartPersistencePort.deleteCart(idCart);
		} catch (Exception e) {
			throw new TechnicalException(Constant.ERROR_DELETE_CART);
		}
	}

}
