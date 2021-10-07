package com.ecommerce.tul.product.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.tul.product.infrastructure.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID>{

}
