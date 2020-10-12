package app.util;

import app.model.cart.Cart;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static Cart getCartInSession(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }

    public static void removeCartInSession(HttpServletRequest request){
        request.getSession().removeAttribute("cart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart cart){
        request.getSession().setAttribute("lastCart", cart);
    }

    public static Cart getLastOrderedCartInSession(HttpServletRequest request){
        return (Cart) request.getSession().getAttribute("lastCart");
    }
}
