package io.github.denkoch.mycosts.service;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Collection<Category> getCategories(Integer userId) {
        return categoryRepository.findAllByUserId(userId);
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    public void addCategory(Integer userId, Category category) {
//        if (categoryRepository.findAllByUserId(userId).stream().noneMatch(cat -> cat.getLabel().equals(category.getLabel()))) {
//            categoryRepository.save(createId(), category);
        if (categoryRepository.findAllByUserId(category.getUserId()).stream().noneMatch(cat -> cat.getLabel().equals(category.getLabel()))) {
            categoryRepository.save(category.getId(), category);
        }
    }


    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Integer createId() {
        return categoryRepository.maxId()+1;
    }

}
