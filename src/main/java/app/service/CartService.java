package app.service;

import app.model.cart.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {

    Cart addProduct(HttpServletRequest request, HttpServletResponse response, Integer id);
    Cart deleteProduct(Integer id, HttpServletRequest request, HttpServletResponse response);
}
