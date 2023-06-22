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
import packagetracking.Contrat.DeliveryRequestWrapper;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.Package;
import packagetracking.model.User;
import packagetracking.service.DeliveryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/request")
public class RequestManagementController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping(value = {"/details/{requestID}"})
    public ResponseEntity<?>  details(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        return ResponseEntity.ok().body(deliveryRequest);
    }

    @GetMapping(value = {"/process/{requestID}"})
    public String processRequest(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        deliveryRequest.setStatus("Processed");
        String randomString = generateRandomString();
        Package deliveryPackage = new Package("P-"+randomString);
        deliveryRequest.setRequestPackage(deliveryPackage);
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/admin";
    }

    @GetMapping(value = {"/ship/{requestID}"})
    public String shippedRequest(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        deliveryRequest.setStatus("Shipped");
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/admin";
    }


    @GetMapping(value = {"/deliver/{requestID}"})
    public String deliverRequest(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        deliveryRequest.setStatus("Delivered");
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/admin";
    }



    @GetMapping(value = {"/cancel/{requestID}"})
    public String cancelRequest(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        deliveryRequest.setStatus("Canceled");
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/admin";
    }

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @GetMapping(value = {"/search/{searchString}"})
    public ResponseEntity<?> searchStudentsUsingAjax(@PathVariable String searchString) {
        List<DeliveryRequest> deliveryRequests = deliveryService.searchDeliveryRequests(searchString);
        return ResponseEntity.ok().body(deliveryRequests);
    }
}
