package io.github.denkoch.mycosts.controller;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.entities.PaymentType;
import io.github.denkoch.mycosts.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ControllerAdvice
public class PaymentContollerAdvice {

    @Autowired
    private CategoryService categoryService;

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
}
