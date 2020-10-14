package app.controller;

import app.model.Category;
import app.model.Product;
import app.model.User;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    public static final Logger log = LoggerFactory.getLogger("ProfileController");

    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getProfilePage(HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        User user = userService.findByEmail(username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("user",user);
        return modelAndView;
    }


    @GetMapping("/editProfile/{id}")
    public ModelAndView editPage(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
//        List<Category> categories = categoryService.allCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProfile");
        modelAndView.addObject("user", user);
//        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }

    @PostMapping("/editProfile")
    public ModelAndView editProduct(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        userService.edit(user);
        return modelAndView;
    }
}
