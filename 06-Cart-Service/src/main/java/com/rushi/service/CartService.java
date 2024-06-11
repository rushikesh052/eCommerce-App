package com.rushi.service;

import com.rushi.dto.CartDto;

public interface CartService {

	public CartDto addToCart(CartDto cartDto);

	public CartDto updateCartQuantityById(CartDto cartDto);

	public CartDto getCartByUserId(Integer userId);

	public void deleteCartById(Integer cartId);

}
