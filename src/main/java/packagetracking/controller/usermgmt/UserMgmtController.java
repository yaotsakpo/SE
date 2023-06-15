package packagetracking.controller.usermgmt;

import packagetracking.model.User;
import packagetracking.service.RoleService;
import packagetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserMgmtController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserMgmtController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"/secured/services/admin/usrmgmt/list","/packagetracking/secured/services/admin/usrmgmt/list"})
    public ModelAndView displayUsersList() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.getAllUsers());
        mav.setViewName("secured/services/admin/usrmgmt/list");
        return mav;
    }

    @GetMapping(value = {"/secured/services/admin/usrmgmt/user/new","/packagetracking/secured/services/admin/usrmgmt/user/new"})
    public ModelAndView displayNewUserForm() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.addObject("roles", roleService.getAllRoles());
        mav.setViewName("secured/services/admin/usrmgmt/newuser");
        return mav;
    }

    @PostMapping(value = {"/secured/services/admin/usrmgmt/user/new","/packagetracking/secured/services/admin/usrmgmt/user/new"})
    public String addNewUser(@Valid @ModelAttribute("user") User user,
            Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/services/admin/usrmgmt/newuser";
        }
        User newAddedUser = userService.saveUser(user);
        return "redirect:/packagetracking/secured/services/admin/usrmgmt/list";
    }

    @GetMapping(value = {"/secured/services/admin/usrmgmt/user/edit/{userId}","/packagetracking/secured/services/admin/usrmgmt/user/edit/{userId}"})
    public String editUser(@PathVariable Integer userId, Model model) {
        User user = userService.getUserById(userId);
        if(user != null) {
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getAllRoles());
            return "secured/services/admin/usrmgmt/edituser";
        }
        return "secured/services/admin/usrmgmt/list";
    }

    @PostMapping(value = {"/secured/services/admin/usrmgmt/user/edit","/packagetracking/secured/services/admin/usrmgmt/user/edit"})
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/services/admin/usrmgmt/edituser";
        }
        user = userService.saveUser(user);
        return "redirect:/packagetracking/secured/services/admin/usrmgmt/list";
    }
}
