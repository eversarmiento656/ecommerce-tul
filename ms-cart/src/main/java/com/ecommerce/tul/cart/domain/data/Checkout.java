package com.ecommerce.tul.cart.domain.data;

import java.io.Serializable;

public class Checkout implements Serializable {

	private static final long serialVersionUID = 1L;
	private double total;
	private String state;

	public Checkout(double total) {
		super();
		this.total = total;
		this.state = stateEnum.COMPLETE.getState();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
