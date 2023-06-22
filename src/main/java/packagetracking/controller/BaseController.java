package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import packagetracking.Contrat.DeliveryRequestWrapper;
import packagetracking.Contrat.InvoiceWrapper;
import packagetracking.model.Address;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.Invoice;
import packagetracking.model.User;
import packagetracking.service.DeliveryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


@ControllerAdvice
public class BaseController {

    @Autowired
    DeliveryService deliveryService;

    @ModelAttribute
    public void globalAttributes(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!isAjaxRequest(request)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            model.addAttribute("username", username);

            // Check the user's role
            boolean isAdmin = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN"));


//         Perform redirect based on user's role
            if (isAdmin) {
                redirectAttributes.addAttribute("adminRedirect", true);
            } else {
                redirectAttributes.addAttribute("userRedirect", true);
            }
        }
    }

    @RequestMapping(value = {"/","/default","/user"})
    public String defaultPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
        if (isAdmin) {
            return "redirect:/admin";
        } else {
            return "redirect:/packagetracking";
        }

    }

    @GetMapping(value = {"/admin"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object adminPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/packagetracking";
        }
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllPendingDelivery();
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.addObject("InvoiceWrapper", new InvoiceWrapper());
        mav.setViewName("secured/index");
        return mav;
    }

    @GetMapping(value = {"/shipped"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object adminShippedPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/packagetracking";
        }
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllShippedDelivery();
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.setViewName("secured/ShippedRequest");
        return mav;
    }

    @GetMapping(value = {"/delivered"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object adminDeliveredPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/packagetracking";
        }
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllDeliveredDelivery();
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.setViewName("secured/DeliveredRequest");
        return mav;
    }

    @GetMapping(value = {"/processed"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object adminProcessedPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/packagetracking";
        }
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllProcessedDelivery();
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.setViewName("secured/ProcessedRequest");
        return mav;
    }

    @GetMapping(value = {"/canceled"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object adminCanceledPage (
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(userRedirect){
            return "redirect:/packagetracking";
        }
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllCanceledDelivery();
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.setViewName("secured/canceledRequest");
        return mav;
    }

    @GetMapping(value = {"/packagetracking","/public/home", "/packagetracking/public/home"})
    public String userPage(
            @RequestParam(value = "adminRedirect", defaultValue = "false") boolean adminRedirect,
            @RequestParam(value = "userRedirect", defaultValue = "false") boolean userRedirect) {
        if(adminRedirect){
            return "redirect:/admin";
        }
        return "public/index";
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}

