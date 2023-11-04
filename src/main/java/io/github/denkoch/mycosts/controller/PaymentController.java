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
@RequestMapping(value = "/users/{id}")
public class PaymentController {

    private final PaymentService paymentService;
    private final CategoryService categoryService;

    public PaymentController(PaymentService paymentService, CategoryService categoryService) {
        this.paymentService = paymentService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("id")
    public Integer populateId(@PathVariable Integer id) {
        return id;
    }

    @ModelAttribute("allPaymentsTypes")
    public List<PaymentType> populateTypes() {
        return Arrays.asList(PaymentType.ALL);
    }

    @ModelAttribute("allCategories")
    public Collection<Category> populateCategories() {
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String getPayments(Model model) {

        model.addAttribute("payments", paymentService.getPayments());
        model.addAttribute("payment", new Payment(paymentService.getMaxId() + 1));
        return "payments";
    }


    @RequestMapping(value = "/payments", method = RequestMethod.POST, params = {"add"})
    public String postPayments(@RequestParam("category-id") Integer categoryId,
                               @ModelAttribute("payment") Payment payment) {

        payment.setCategory(categoryService.getCategory(categoryId));
        paymentService.addPayment(payment);

        return "redirect:payments";
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST, params = {"edit"})
    public String postPayments(@RequestParam("payment-id") Integer paymentId) {
        if (paymentService.getPayment(paymentId) != null) {
            return "redirect:payments/" + paymentId;
        }
        return "redirect:payments";
    }

}
