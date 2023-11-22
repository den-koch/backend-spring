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
@RequestMapping(value = "/users/{userId}/payments")
public class PaymentEditController {

    private final PaymentService paymentService;
    private final CategoryService categoryService;

    public PaymentEditController(PaymentService paymentService, CategoryService categoryService) {
        this.paymentService = paymentService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("userId")
    public Integer populateUserId(@PathVariable Integer userId) {
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


    @RequestMapping(value = "{paymentId}", method = RequestMethod.GET)
    public String getPayment(@PathVariable Integer paymentId, Model model) {

        model.addAttribute("paymentId", paymentId);
        model.addAttribute("payment", paymentService.getPayment(paymentId));
        return "payment-edit";
    }

    @RequestMapping(value = "{paymentId}", method = RequestMethod.POST, params = {"edit"})
    public String postPayment(@PathVariable Integer paymentId,
                              @ModelAttribute Payment payment) {

        paymentService.updatePayment(paymentId, payment);

        return "redirect:{paymentId}";
    }

    @RequestMapping(value = "{paymentId}", method = RequestMethod.POST, params = {"delete"})
    public String postPayment(@PathVariable Integer paymentId) {

        paymentService.deletePayment(paymentId);

        return "redirect:../payments";
    }
}
