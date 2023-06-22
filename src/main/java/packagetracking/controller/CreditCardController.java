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
import packagetracking.model.CreditCard;
import packagetracking.model.DeliveryRequest;
import packagetracking.model.User;
import packagetracking.service.CreditCardService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/payment_option")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @GetMapping(value = {"","/list"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ModelAndView paymentOption() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        List<CreditCard> creditCards = creditCardService.getAllCreditCards(connectedUser.getUserId());
        modelAndView.addObject("creditCards", creditCards);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("creditCardsCount", creditCards.size());
        modelAndView.addObject("CreditCard", new CreditCard());
        modelAndView.setViewName("public/payment_option");
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String AddPaymentOption(@ModelAttribute("CreditCard") CreditCard CreditCard,
                                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/payment_option";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        CreditCard.setUser(connectedUser);
        CreditCard.setPreferredCard(false);
        creditCardService.saveCreditCard(CreditCard);
        return "redirect:/payment_option/list";
    }


    @GetMapping(value = {"/edit/{CreditCardId}"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CreditCard> editCreditCard(@PathVariable Integer CreditCardId) {
        CreditCard creditCard = creditCardService.getCreditCardById(CreditCardId);
        return ResponseEntity.ok().body(creditCard);
    }

    @PostMapping(value = {"/edit"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String updateCreditCard(@Valid @ModelAttribute("CreditCard") CreditCard CreditCard,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "CreditCard/edit";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        CreditCard.setUser(connectedUser);

        creditCardService.saveCreditCard(CreditCard);
        return "redirect:/payment_option/list";
    }

    @GetMapping(value = {"/delete/{CreditCardId}"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String deleteStudent(@PathVariable Integer CreditCardId) {
        creditCardService.deleteCreditCardById(CreditCardId);
        return "redirect:/payment_option/list";
    }

    @GetMapping(value = {"/preferred/{CreditCardId}"})
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String setPreference(@PathVariable Integer CreditCardId) {

        CreditCard creditCard = creditCardService.getCreditCardById(CreditCardId);
        boolean previousValue = creditCard.isPreferredCard();
        creditCard.setPreferredCard(!previousValue);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        CreditCard preferredCard = creditCardService.getPreferred(connectedUser.getUserId());
        if(preferredCard != null){
            preferredCard.setPreferredCard(false);
            creditCardService.saveCreditCard(preferredCard);
        }
        creditCardService.saveCreditCard(creditCard);


        return "redirect:/payment_option/list";
    }

    @GetMapping(value = {"/search/{searchString}"})
    public ResponseEntity<?> searchCreditCardUsingAjax(@PathVariable String searchString) {
        List<CreditCard> creditCards = creditCardService.searchCreditCards(searchString);
        return ResponseEntity.ok().body(creditCards);
    }


}
