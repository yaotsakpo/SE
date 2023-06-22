package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import packagetracking.Contrat.DeliveryRequestWrapper;
import packagetracking.model.Address;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.Package;
import packagetracking.model.User;
import packagetracking.service.AddressService;
import packagetracking.service.DeliveryService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/package")
public class PackageController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    AddressService addressService;

    @GetMapping(value = {"","list"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ModelAndView list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        ModelAndView mav = new ModelAndView();
        List<DeliveryRequest> deliveryRequests = deliveryService.getAllDeliveryRequests(connectedUser.getUserId());
        List<Address> addressList = addressService.getAllAddress(connectedUser.getUserId());
        mav.addObject("deliveryRequests", deliveryRequests);
        mav.addObject("userAddress", addressList);
        mav.addObject("searchString", "");
        mav.addObject("requestCount", deliveryRequests.size());
        mav.addObject("DeliveryRequest", new DeliveryRequestWrapper());
        mav.setViewName("public/tracking_list");
        return mav;
    }

    @PostMapping(value = {"/add"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String add(@ModelAttribute("DeliveryRequest") DeliveryRequestWrapper DeliveryRequestWrapper,
                      BindingResult bindingResult, Model model) {
        System.out.println(DeliveryRequestWrapper);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        DeliveryRequest deliveryRequest = new DeliveryRequest(
                LocalDate.now(),
                "Pending",
                connectedUser,
                addressService.getAddressById(DeliveryRequestWrapper.getDeliveryAddressID()),
                addressService.getAddressById(DeliveryRequestWrapper.getPickupAddressID()),
                DeliveryRequestWrapper.getWeight(),
                DeliveryRequestWrapper.getHeight()
        );
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/package";
    }
}
