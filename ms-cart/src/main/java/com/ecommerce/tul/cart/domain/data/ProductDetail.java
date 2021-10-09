package com.ecommerce.tul.cart.domain.data;

import java.io.Serializable;
import java.util.UUID;

public class ProductDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private UUID sku;
	private String name;
	private Integer chosenQuantity;
	private double priceUnit;

	public UUID getSku() {
		return sku;
	}

	public void setSku(UUID sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChosenQuantity() {
		return chosenQuantity;
	}

	public void setChosenQuantity(Integer chosenQuantity) {
		this.chosenQuantity = chosenQuantity;
	}

	public double getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(double priceUnit) {
		this.priceUnit = priceUnit;
	}

}
