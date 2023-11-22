package io.github.denkoch.mycosts.controller;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.entities.PaymentType;
import io.github.denkoch.mycosts.service.CategoryService;
import io.github.denkoch.mycosts.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/users/{userId}")
public class PaymentController {

    private final PaymentService paymentService;
    private final CategoryService categoryService;

    public PaymentController(PaymentService paymentService, CategoryService categoryService) {
        this.paymentService = paymentService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("userId")
    public Integer populateId(@PathVariable Integer userId) {
        return userId;
    }

    @ModelAttribute("allPaymentsTypes")
    public List<PaymentType> populateTypes() {
        return Arrays.asList(PaymentType.ALL);
    }

    @ModelAttribute("allCategories")
    public Collection<Category> populateCategories(@PathVariable Integer userId) {
        return categoryService.getCategories(userId);
    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String getPayments(@PathVariable Integer userId, Model model) {

        model.addAttribute("payments", paymentService.getPayments(userId));
        model.addAttribute("payment", new Payment(paymentService.createId(), userId));
        return "payments";
    }


    @RequestMapping(value = "/payments", method = RequestMethod.POST, params = {"add"})
    public String postPayments(@ModelAttribute("payment") Payment payment) {

        paymentService.addPayment(payment);

        return "redirect:payments";
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST, params = {"edit"})
    public String postPayments(@PathVariable Integer userId,
                               @RequestParam("payment-id") Integer paymentId) {
        Payment temp = paymentService.getPayment(paymentId);
        if (temp != null && temp.getUserId().equals(userId)) {
            return "redirect:payments/" + paymentId;
        }
        return "redirect:payments";
    }

}
