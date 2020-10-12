package app.controller;

import app.model.User;
import app.model.cart.OrderDTO;
import app.model.cart.OrderListDTO;
import app.model.cart.ProductDTO;
import app.service.OrderService;
import app.service.ProductService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
public class AdminController {
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/orderList")
    public ModelAndView orderList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView product(@RequestParam(value = "id", defaultValue = "") Integer id){
        ProductDTO productDTO = null;
        if (id != null)
            productDTO = productService.getProductDTOById(id);
        if (productDTO == null){
            productDTO = new ProductDTO();
            productDTO.setNewProduct(true);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.addObject("product", productDTO);
        return modelAndView;
    }

    @PostMapping("/product")
//    аналогично
    @Transactional(propagation = Propagation.NEVER)
    public ModelAndView productSave(@ModelAttribute("product") ProductDTO productDTO){
        ModelAndView modelAndView = new ModelAndView();
        try {
            productService.save(productDTO);
        }catch (Exception e){
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("product");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    @GetMapping("/order")
    public ModelAndView orderView(@RequestParam("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        OrderDTO orderDTO = null;
        if (id !=null)
            orderDTO = this.orderService.getOrderDTOById(id);
        if (orderDTO == null){
            modelAndView.setViewName("redirect:/orderList");
            return modelAndView;
        }
        List<OrderListDTO> list = this.orderService.listOrderDTO(id);
        orderDTO.setList(list);
        modelAndView.setViewName("order");
        modelAndView.addObject("orderDTO", orderDTO);
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }
}
