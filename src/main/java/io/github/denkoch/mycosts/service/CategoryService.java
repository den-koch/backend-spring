package io.github.denkoch.mycosts.service;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.repository.CategoryRepository;

import java.util.Collection;
import java.util.List;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        if (!categoryRepository.findAll().stream().anyMatch(cat -> cat.getLabel().equals(category.getLabel()))) {
            categoryRepository.save(getMaxId() + 1, category);
        }
    }

    public Category getCategory(String label) {
        return categoryRepository.findByLabel(label);
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Integer getMaxId() {
        return categoryRepository.findAll().stream().mapToInt(Category::getId).max().orElse(0);
    }

}
