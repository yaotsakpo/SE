package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import packagetracking.model.CreditCard;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.User;
import packagetracking.service.CreditCardService;
import packagetracking.service.DeliveryService;

import java.util.List;

@Controller
public class MainController extends BaseController {

    @GetMapping(value = {"/profile"})
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        mav.addObject("user", user);
        mav.setViewName("public/profile");
        return mav;
    }

    @GetMapping(value = {"/about","/public/about","/packagetracking/about"})
    public String about() {
        return "public/about";
    }

}
