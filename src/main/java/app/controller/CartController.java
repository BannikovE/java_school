package app.controller;

import app.model.cart.Cart;
import app.util.Utils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/finalize")
    public ModelAndView finalizeCart(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Cart cart){
        ModelAndView modelAndView = new ModelAndView();
        Utils.setCartToCookie(request, response, cart);
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            modelAndView.setViewName("redirect:/cartUser");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/cartFinalize");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @GetMapping("/confirmation")
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
