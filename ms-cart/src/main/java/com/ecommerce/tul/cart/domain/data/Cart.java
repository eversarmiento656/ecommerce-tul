package com.ecommerce.tul.cart.domain.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private List<ProductDetail> listProduct;
	private String state;

	public Cart(String id) {
		super();
		this.id = id;
		this.listProduct = new ArrayList<>();
		this.state = stateEnum.PENDING.getState();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProductDetail> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductDetail> listProduct) {
		this.listProduct = listProduct;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getTotal() {
		return this.listProduct.stream().mapToDouble(element -> element.getChosenQuantity() * element.getPriceUnit())
				.sum();
	}
}
