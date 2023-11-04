package io.github.denkoch.mycosts.service;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        if (categoryRepository.findAll().stream().noneMatch(cat -> cat.getLabel().equals(category.getLabel()))) {
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
