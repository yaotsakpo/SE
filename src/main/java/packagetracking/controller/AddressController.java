package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import packagetracking.model.Address;
import packagetracking.model.CreditCard;
import packagetracking.model.User;
import packagetracking.service.AddressService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = {"","/list"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ModelAndView paymentOption() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        List<Address> address = addressService.getAllAddress(connectedUser.getUserId());
        modelAndView.addObject("address", address);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("addressCount", address.size());
        modelAndView.addObject("Address", new Address());
        modelAndView.setViewName("public/address");
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String AddPaymentOption(@ModelAttribute("Address") Address Address,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/address";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        Address.setUser(connectedUser);
        addressService.saveAddress(Address);
        return "redirect:/address/list";
    }


    @GetMapping(value = {"/edit/{AddressId}"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Address> editAddress(@PathVariable Integer AddressId) {
        Address address = addressService.getAddressById(AddressId);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping(value = {"/edit"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String updateAddress(@Valid @ModelAttribute("Address") Address Address,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Address/edit";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        Address.setUser(connectedUser);

        addressService.saveAddress(Address);
        return "redirect:/address/list";
    }

    @GetMapping(value = {"/delete/{AddressId}"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String deleteStudent(@PathVariable Integer AddressId) {
        addressService.deleteAddressById(AddressId);
        return "redirect:/address/list";
    }

    @GetMapping(value = {"/search/{searchString}"})
    public ResponseEntity<?> searchAddressUsingAjax(@PathVariable String searchString) {
        List<Address> addresses = addressService.searchAddress(searchString);
        return ResponseEntity.ok().body(addresses);
    }
    
}
