package io.github.denkoch.mycosts.controller;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.service.CategoryService;
import io.github.denkoch.mycosts.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping(value = "/users/{userId}/payments")
public class PaymentFilterController {

    private final PaymentService paymentService;
    private final CategoryService categoryService;

    public PaymentFilterController(PaymentService paymentService, CategoryService categoryService) {
        this.paymentService = paymentService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("userId")
    public Integer populateId(@PathVariable Integer userId) {
        return userId;
    }

    @ModelAttribute("allCategories")
    public Collection<Category> populateCategories(@PathVariable Integer userId) {
        return categoryService.getCategories(userId);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String getFilteredPayments(@PathVariable Integer userId,
                                      @ModelAttribute("from-date") LocalDate fromDate,
                                      @ModelAttribute("to-date") LocalDate toDate,
                                      @ModelAttribute("category-id") Integer categoryId, Model model) {

        if (fromDate == null) fromDate = paymentService.getLowestDate();
        if (toDate == null) toDate = paymentService.getHighestDate();

        model.addAttribute("payments", paymentService.getFilteredPayments(userId, fromDate, toDate, categoryId));

        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("category", categoryService.getCategory(categoryId));
        return "filters";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, params = {"filter"})
    public String postPayments(@RequestParam(value = "from-date", required = false) LocalDate fromDate,
                               @RequestParam(value = "to-date", required = false) LocalDate toDate,
                               @RequestParam(value = "category-id", defaultValue = "0", required = false) Integer categoryId,
                               RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("from-date", fromDate);
        redirectAttributes.addFlashAttribute("to-date", toDate);
        redirectAttributes.addFlashAttribute("category-id", categoryId);

        return "redirect:filter";
    }

}
