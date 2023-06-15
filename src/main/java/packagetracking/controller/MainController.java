package packagetracking.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import packagetracking.model.User;

@Controller
public class MainController extends BaseController {

    @GetMapping(value = {"/new_package"})
    public String new_package() {
        return "public/new_package";
    }

    @GetMapping(value = {"/tracking_list"})
    public String tracking_list() {
        return "public/tracking_list";
    }

    @GetMapping(value = {"/profile"})
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        mav.addObject("user", user);
        mav.setViewName("public/profile");
        return mav;
    }


}
