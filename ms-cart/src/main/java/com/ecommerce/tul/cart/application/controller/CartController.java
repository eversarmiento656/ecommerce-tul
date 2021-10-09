package com.ecommerce.tul.cart.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.tul.cart.domain.data.Cart;
import com.ecommerce.tul.cart.domain.data.Checkout;
import com.ecommerce.tul.cart.domain.data.ProductDetail;
import com.ecommerce.tul.cart.domain.port.CartServicePort;

@RestController
@RequestMapping("v1")
public class CartController {

	@Autowired
	private CartServicePort cartServicePort;

	@GetMapping("/cart/{id}")
	public Cart getCart(@PathVariable String id) {
		return this.cartServicePort.getCartById(id);
	}

	@PostMapping("/cart/{id}")
	public void addProduct(@RequestBody ProductDetail product, @PathVariable String id) {
		this.cartServicePort.addProductCart(id, product);
	}

	@PostMapping("/cart")
	@ResponseStatus(HttpStatus.CREATED)
	public Cart addProduct(@RequestBody ProductDetail product) {
		return this.cartServicePort.addProductCart("", product);
	}

	@DeleteMapping("/cart/{idCart}/{idProduct}")
	public void deleteProductCart(@PathVariable String idCart, @PathVariable String idProduct) {
		this.cartServicePort.deleteProductCart(idCart, idProduct);
	}

	@PutMapping("/cart/{idCart}")
	public void deleteProductCart(@PathVariable String idCart, @RequestBody ProductDetail product) {
		this.cartServicePort.updateProductCart(idCart, product);
	}

	@PostMapping("/checkout/{idCart}")
	public Checkout checkout(@PathVariable String idCart) {
		return this.cartServicePort.checkout(idCart);
	}

	@DeleteMapping("/cart/{idCart}")
	public void deleteCart(@PathVariable String idCart) {
		this.cartServicePort.deleteCart(idCart);
	}
}
