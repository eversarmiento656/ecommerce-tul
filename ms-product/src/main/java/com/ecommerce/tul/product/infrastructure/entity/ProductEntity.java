package com.ecommerce.tul.product.infrastructure.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "uuid", updatable = false)
	@GeneratedValue(generator = "uuid")
	private UUID sku;
	private String name;
	private String description;
	private Double price;

	public ProductEntity(UUID sku, String name, String description, Double price) {
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductEntity() {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
