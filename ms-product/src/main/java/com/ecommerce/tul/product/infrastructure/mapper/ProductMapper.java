package com.ecommerce.tul.product.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.tul.ecommercecommons.models.Product;
import com.ecommerce.tul.product.infrastructure.entity.ProductEntity;

@Mapper
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	Product entityToProduct(ProductEntity entity);

	ProductEntity productToEntity(Product entity);
}
