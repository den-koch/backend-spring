package io.github.denkoch.mycosts.controller;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users/{userId}")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("userId")
    public Integer populateId(@PathVariable Integer userId) {
        return userId;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getCategories(@PathVariable Integer userId, Model model) {

        model.addAttribute("categories", categoryService.getCategories(userId));
        model.addAttribute("category", new Category(categoryService.createId(), userId));
        return "categories";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, params = {"add"})
    public String postPayments(@PathVariable Integer userId,
                               @ModelAttribute("category") Category category) {

        categoryService.addCategory(userId, category);

        return "redirect:categories";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, params = {"delete"})
    public String postPayments(@PathVariable Integer userId,
                               @RequestParam("category-id") Integer categoryId) {

        Category temp = categoryService.getCategory(categoryId);
        if (temp != null && temp.getUserId().equals(userId)) {
            categoryService.deleteCategory(categoryId);
        }

        return "redirect:categories";
    }

}
