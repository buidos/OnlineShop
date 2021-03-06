package by.dmitrui98.controller;

import by.dmitrui98.entity.User;
import by.dmitrui98.service.dao.UserService;
import by.dmitrui98.service.security.SecurityService;
import by.dmitrui98.validation.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Администратор on 14.04.2017.
 */
@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

//        try {
//            userForm.setFname(new String (userForm.getFname().getBytes("ISO-8859-1"), "UTF-8"));
//            userForm.setSurname(new String (userForm.getSurname().getBytes("ISO-8859-1"), "UTF-8"));
//            userForm.setLname(new String (userForm.getLname().getBytes("ISO-8859-1"), "UTF-8"));
//            userForm.setPhone(new String (userForm.getPhone().getBytes("ISO-8859-1"), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            logger.error("UnsupportedEncodingException (/registration).", e);
//        }

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getLogin(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        System.out.println("welcome");
        return "welcome";
    }

    @GetMapping(value = "/comeIn")
    public String comeIn(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "comeIn";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/comeIn?logout";
    }

}
