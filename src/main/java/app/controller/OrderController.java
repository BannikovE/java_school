package app.controller;

import app.model.Order;
import app.model.cart.Cart;
import app.service.OrderService;
import app.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ModelAndView createOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Cart cart){
        ModelAndView modelAndView = new ModelAndView();

        Order order = orderService.save(cart);

        Utils.cleanCookie(request);

        modelAndView.setViewName("show");
        modelAndView.setViewName("redirect:/show");
        modelAndView.addObject("order", order);
        return modelAndView;
    }
}
