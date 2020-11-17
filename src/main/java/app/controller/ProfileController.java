package app.controller;

import app.model.Address;
import app.model.User;
import app.model.cart.Cart;
import app.service.AddressService;
import app.service.UserService;
import app.util.AppUtils;
import app.util.CartCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    public static final Logger log = LoggerFactory.getLogger("ProfileController");

    private UserService userService;
    private AddressService addressService;


    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setUserService(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getProfilePage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        if (!AppUtils.isAuthUser()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        String username = request.getUserPrincipal().getName();
        User user = userService.findByEmail(username);
        if (user == null) {
            log.error("User with email {} doesn't exist", username);
            return modelAndView;
        }
        AppUtils.setUserIdInSession(request, user.getId());
        Cart cart = AppUtils.getCartFromSession(request);
        if (cart != null) {
            CartCacheUtils.mergeCart(request, cart);
        }
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @GetMapping("/editProfile/{id}")
    public ModelAndView editPage(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProfile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/editProfile")
    public ModelAndView editProduct(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        User userAfterUpdate = userService.edit(user);
        if (userAfterUpdate != null) {
            try {
                modelAndView.addObject("user", userAfterUpdate);
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return modelAndView;
    }

    @GetMapping("/editAddress/{id}")
    public ModelAndView editAddressPage(@PathVariable("id") Integer addressId){
        ModelAndView modelAndView = new ModelAndView();
        Address address = addressService.findAddressById(addressId);
        modelAndView.setViewName("editAddress");
        modelAndView.addObject("address", address);
        return modelAndView;
    }

    @PostMapping("/editAddress")
    public ModelAndView editProduct(@ModelAttribute("address") Address address){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        addressService.edit(address);
        return modelAndView;
    }

    @GetMapping("/addAddress/{id}")
    public ModelAndView addAddressPage(@PathVariable("id") Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findById(userId);
        modelAndView.setViewName("addAddress");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/addAddress")
    public ModelAndView addAddress(@ModelAttribute("address") Address address){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        try {
            int resultId = addressService.addAddress(address);
            log.info("Created address with id {}", resultId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return modelAndView;
    }
}
