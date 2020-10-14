package app.util;

import app.model.cart.Cart;
import app.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utils {

    public static final Logger log = LoggerFactory.getLogger(Utils.class);
    /**
     * Название куки для хранения корзины
     */
    public static final String CART_COOKIE_NAME = "cart";
    /**
     * 30 дней
     */
    private static final int COOKIE_MAX_AGE = 2592000;

    private static final Gson gson = new Gson();

    public static Cart getCartFromCookie(HttpServletRequest request){
        Cookie cookie = getCookieByName(request, CART_COOKIE_NAME);
        try {
            return cookie != null ? gson.fromJson(URLDecoder.decode(cookie.getValue(), "UTF-8"), Cart.class) : new Cart();
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static void setCartToCookie(HttpServletRequest request, HttpServletResponse response, Cart cart) {
        if (cart != null && request !=null && response != null) {
            Cookie cookie = Utils.getCookieByName(request, Utils.CART_COOKIE_NAME);
            try {
                String cartToCookie = URLEncoder.encode(gson.toJson(cart), "UTF-8");
                if (cookie == null) {
                    cookie = new Cookie(CART_COOKIE_NAME, cartToCookie);
                    response.addCookie(cookie);
                } else {
                    cookie.setValue(cartToCookie);
                }
                cookie.setMaxAge(COOKIE_MAX_AGE);
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void cleanCookie(HttpServletRequest request) {
        Cookie cookie = getCookieByName(request, CART_COOKIE_NAME);
        if (cookie != null) {
            cookie.setValue(null);
        }
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie c: cookies) {
                if(name.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
