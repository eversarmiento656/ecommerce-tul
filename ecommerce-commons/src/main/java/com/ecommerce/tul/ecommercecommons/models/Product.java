package com.ecommerce.tul.ecommercecommons.models;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private UUID sku;
	private String name;
	private String description;
	private Double price;
	private boolean discount;
	private int quantity;

	public Product(UUID sku, String name, String description, Double price, boolean discount, int quantity) {
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
	}

	public Product() {
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
