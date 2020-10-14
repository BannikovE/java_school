package app.controller;

import app.model.User;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    public static final Logger log = LoggerFactory.getLogger("AuthController");

    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/signUp")
    public ModelAndView getSignUpPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/signUp");
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView addUser(HttpServletRequest request,
                                @ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/signUp");
            return modelAndView;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            modelAndView.setViewName("/signUp");
            modelAndView.addObject("passwordError", "Пароли не совпадают");
            return modelAndView;
        }
        if (!userService.saveUser(userForm, request)){
            modelAndView.setViewName("/signUp");
            modelAndView.addObject("usernameError",
                    "Пользователь с таким именем уже существует.");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        try {
//            int resultId = userService.add(user);
//            log.info("Created client with id {}", resultId);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
        return modelAndView;
    }
}
