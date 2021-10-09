package com.ecommerce.tul.cart.infrastructure.adapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ecommerce.tul.cart.domain.data.Cart;
import com.ecommerce.tul.cart.domain.port.CartPersistencePort;
import com.ecommerce.tul.cart.domain.util.Constant;

public class CartRedisAdapter implements CartPersistencePort {


	@Autowired
	private RedisTemplate<String, Cart> redisTemplate;

	private HashOperations<String, String, Cart> hashOperations;

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Cart findById(String id) {
		return this.hashOperations.get(Constant.KEY_CART, id);
	}

	@Override
	public String addProductCart(Cart cart) {
			hashOperations.put(Constant.KEY_CART, cart.getId(), cart);
			return cart.getId();
	}

	@Override
	public boolean deleteCart(String id) {
			hashOperations.delete(Constant.KEY_CART, id);
			return true;
	}

}
