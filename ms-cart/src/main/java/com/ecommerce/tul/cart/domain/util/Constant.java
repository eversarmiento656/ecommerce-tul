package com.ecommerce.tul.cart.domain.util;

public final class Constant {
	public static final String KEY_CART = "Cart";
	public static final String ERROR_CHECKOUT = "ocurrió un error al hacer el checkout";
	public static final String ERROR_DELETE_CART = "ocurrió un error al eliminar el cart";
	public static final String ERROR_FIND_CART = "ocurrió un error al buscar el cart por id";
	public static final String ERROR_ADD_CART = "ocurrió un error al intentar agregar el producto al carrito";
	public static final String ERROR_FIND_PRODUCT = "no se encontro un producto con el id -> %s";
	public static final String ERROR_DELETE_PRODUCT = "no se pudo eliminar un producto con el -> %s";
	public static final String ERROR_DELETE_PRODUCT_TECHNICAL = "ocurrió un error al intentar eliminar el producto";
	public static final String ERROR_UPDATE_LIST_CART = "ocurrió un error al intentar actualizar el producto del carrito";
	public static final String ERROR_QUANTITY = "ocurrió un error, el producto (%s) tiene %d elementos en stock";

	private Constant() {
	}
}
