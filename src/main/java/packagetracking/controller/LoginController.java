package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import packagetracking.model.Role;
import packagetracking.model.User;
import packagetracking.service.RoleService;
import packagetracking.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"/public/login","/packagetracking/public/login"})
    public String login() {
        return "public/login";
    }

    @GetMapping(value = {"/public/signup","/packagetracking/public/signup"})
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("public/signup");
        return mav;
    }

    @GetMapping(value = {"/public/adminsignup","/packagetracking/public/adminsignup"})
    public ModelAndView adminSignup() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("public/adminsignup");
        return mav;
    }


    @PostMapping(value = {"/public/signup","/packagetracking/public/signup"})
    public String addNewCustomerUser(@Valid @ModelAttribute("user") User user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/public/login";
        }
        List<Role> roles = new ArrayList<>();
        Role role = roleService.getRoleByName("ROLE_CUSTOMER");
        roles.add(role);
        user.setRoles(roles);
        User newAddedUser = userService.saveUser(user);
        return "redirect:/packagetracking/public/home";
    }

    @PostMapping(value = {"/public/adminsignup","/packagetracking/public/adminsignup"})
    public String addNewAdminUser(@Valid @ModelAttribute("user") User user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/public/login";
        }
        List<Role> roles = new ArrayList<>();
        Role role = roleService.getRoleByName("ROLE_ADMIN");
        roles.add(role);
        user.setRoles(roles);
        User newAddedUser = userService.saveUser(user);
        return "redirect:/packagetracking/public/home";
    }

}
