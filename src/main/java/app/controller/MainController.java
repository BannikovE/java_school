package app.controller;

import app.model.Product;
import app.model.cart.Cart;
import app.model.cart.ProductDTO;
import app.model.cart.UserDTO;
import app.service.OrderService;
import app.service.ProductService;
import app.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/buyProduct"})
    public ModelAndView listProductHandler(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "id", defaultValue = "") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Product product = null;
        if (id != null)
            product = productService.getProductById(id);
        if (product != null){
            Cart cart = Utils.getCartFromCookie(request);
            ProductDTO productDTO = new ProductDTO(product);
            cart.addProduct(productDTO, 1);
            Utils.setCartToCookie(request, response, cart);
        }
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView cartHandler(HttpServletRequest request){
        Cart cart = Utils.getCartFromCookie(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @GetMapping("/cartRemoveProduct")
    public ModelAndView removeCartFromProduct(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam(value = "id", defaultValue = "") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Product product = null;
        if (id != null)
            product = productService.getProductById(id);
        if (product != null){
            Cart cart = Utils.getCartFromCookie(request);
            ProductDTO productDTO = new ProductDTO(product);
            cart.removeProduct(productDTO);
            Utils.setCartToCookie(request, response, cart);
        }
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @GetMapping("/cartUser")
    public ModelAndView cartUserForm(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = Utils.getCartFromCookie(request);
        if (cart.isEmpty()){
            modelAndView.setViewName("redirect:/cart");
            return modelAndView;
        }
        UserDTO userDTO = cart.getUserDTO();
        if (userDTO == null)
            userDTO = new UserDTO();
        modelAndView.setViewName("cartUser");
        modelAndView.addObject("user", userDTO);
        return modelAndView;
    }

    @PostMapping("/cartUser")
    public ModelAndView cartUserSave(HttpServletRequest request, @ModelAttribute("user") UserDTO userDTO){
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = Utils.getCartFromCookie(request);
        cart.setUserDTO(userDTO);
        modelAndView.setViewName("redirect:/cartConfirmation");
        return modelAndView;
    }

    @GetMapping("/cartConfirmation")
    public ModelAndView cartConfirmationReview(HttpServletRequest request){
        Cart cart = Utils.getCartFromCookie(request);
        ModelAndView modelAndView = new ModelAndView();
        if (cart.isEmpty()){
            modelAndView.setViewName("redirect:/cart");
            return modelAndView;
        }
        modelAndView.setViewName("cartConfirmation");
        return modelAndView;
    }
}
