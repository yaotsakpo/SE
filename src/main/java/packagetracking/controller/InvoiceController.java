package packagetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import packagetracking.Contrat.InvoiceWrapper;
import packagetracking.model.CreditCard;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.Invoice;
import packagetracking.model.User;
import packagetracking.service.DeliveryService;
import packagetracking.service.InvoiceService;

import java.util.HashMap;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping(value = {"/add"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AddPaymentOption(@ModelAttribute("Invoice") InvoiceWrapper invoiceWrapper,
                                   BindingResult bindingResult, Model model) {

        Invoice invoice = new Invoice(invoiceWrapper.getPrice(),"Sent",invoiceWrapper.getDescription());
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(invoiceWrapper.getRequestID());
        deliveryRequest.setInvoice(invoice);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedAdmin = (User) authentication.getPrincipal();
        deliveryRequest.setValidator(connectedAdmin);
        deliveryService.saveDeliveryRequest(deliveryRequest);

        return "redirect:/admin";
    }

    @GetMapping(value = {"/cancel/{requestID}"})
    public String cancelInvoice(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        deliveryRequest.setStatus("Canceled");
        Invoice invoice = deliveryRequest.getInvoice();
        invoice.setStatus("Rejected");
        deliveryService.saveDeliveryRequest(deliveryRequest);
        return "redirect:/package";
    }

    @GetMapping(value = {"/accept/{requestID}"})
    public String acceptInvoice(@PathVariable Integer requestID) {
        DeliveryRequest deliveryRequest = deliveryService.getDeliveryRequestById(requestID);
        Invoice invoice = deliveryRequest.getInvoice();
        invoice.setStatus("Accepted");
        invoiceService.saveInvoice(invoice);
        return "redirect:/package";
    }

}
