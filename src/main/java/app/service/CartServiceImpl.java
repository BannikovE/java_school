package app.service;

import app.model.Product;
import app.model.cart.Cart;
import app.model.cart.ProductDTO;
import app.util.CartCacheUtils;
import app.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CartServiceImpl implements CartService {

    public static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addProduct(HttpServletRequest request, HttpServletResponse response, Integer id) {
        boolean isAuth = AppUtils.isAuthUser();
        Cart cart = isAuth ? CartCacheUtils.getCart(request) : AppUtils.getCartFromSession(request);
        if (id == null) {
            log.error("Product id doesn't exist!");
            return cart;
        }
        Product product = productService.getProductById(id);
        if (product != null) {
            ProductDTO productDTO = new ProductDTO(product);
            if (cart == null) {
                cart = new Cart();
            }
            cart.addProduct(productDTO, 1);
            if (isAuth) {
                CartCacheUtils.putCart(request, cart);
            } else {
                AppUtils.setCartToSession(request, cart);
            }
        }
        return cart;
    }

    @Override
    public Cart deleteProduct(Integer id, HttpServletRequest request, HttpServletResponse response) {
        boolean isAuth = AppUtils.isAuthUser();
        Cart cart = isAuth ? CartCacheUtils.getCart(request) : AppUtils.getCartFromSession(request);
        if (cart != null) {
            cart.removeProduct(id);
            if (!isAuth) {
                AppUtils.setCartToSession(request, cart);
                return cart;
            }
        }
        return null;
    }
}
