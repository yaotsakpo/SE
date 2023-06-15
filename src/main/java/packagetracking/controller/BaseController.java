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

    @RequestMapping(value = {"/","/default"})
    public String defaultPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }

    }

    @GetMapping(value = {"/admin"})
    public String adminPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/user";
        }
        return "secured/index";
    }

    @GetMapping(value = {"/user","/packagetracking","/public/home", "/packagetracking/public/home"})
    public String userPage(
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(adminRedirect){
            return "redirect:/admin";
        }
        return "public/index";
    }

    @GetMapping(value = {"/about","/public/about","/packagetracking/about"})
    public String about() {
        return "public/about";
    }
}

