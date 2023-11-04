package io.github.denkoch.mycosts.controller;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users/{id}")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("id")
    public Integer populateId(@PathVariable Integer id) {
        return id;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getCategory(Model model) {

        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("category", new Category(categoryService.getMaxId() + 1));
        return "categories";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, params = {"add"})
    public String postPayments(@ModelAttribute("category") Category category) {

        categoryService.addCategory(category);

        return "redirect:categories";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, params = {"delete"})
    public String postPayments(@RequestParam("category-id") Integer categoryId) {

        categoryService.deleteCategory(categoryId);

        return "redirect:categories";
    }

}
