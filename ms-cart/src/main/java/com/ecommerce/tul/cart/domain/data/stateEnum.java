package com.ecommerce.tul.cart.domain.data;

public enum stateEnum {
	PENDING("pending"), COMPLETE("complete");

	private String state;

	stateEnum(String s) {
		this.state = s;
	}

	public String getState() {
		return this.state;
	}
}
