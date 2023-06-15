package packagetracking.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import packagetracking.model.User;


@ControllerAdvice
public class BaseController {

    @ModelAttribute
    public void globalAttributes(Model model, RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);

        // Check the user's role
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));


        // Perform redirect based on user's role
        if (isAdmin) {
            redirectAttributes.addAttribute("adminRedirect", true);
        } else {
            redirectAttributes.addAttribute("userRedirect", true);
        }
    }

    @RequestMapping("/default")
    public String defaultPage(@RequestParam(value = "adminRedirect", required = false) boolean adminRedirect,
                              @RequestParam(value = "userRedirect", required = false) boolean userRedirect) {
        if (adminRedirect) {
            return "redirect:/admin";
        } else if (userRedirect) {
            return "redirect:/user";
        }
        return "default";
    }

    @RequestMapping(value = {"/","/packagetracking/"})
    public String home() {
        return "redirect:/default";
    }

    @GetMapping(value = {"/admin"})
    public String adminPage() {
        return "secured/index";
    }

    @GetMapping(value = {"/user","/packagetracking","/public/home", "/packagetracking/public/home"})
    public String userPage() {
        return "public/index";
    }

    @GetMapping(value = {"/about","/public/about","/packagetracking/about"})
    public String about() {
        return "public/about";
    }
}

