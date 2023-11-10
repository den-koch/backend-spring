package io.github.denkoch.mycosts.repository;

import io.github.denkoch.mycosts.entities.Category;

import java.util.*;

public class CategoryRepository implements ObjectRepository<Category> {

    private Map<Integer, Category> repository = new HashMap<>();

    public CategoryRepository() {
        repository.put(1, new Category(1, "Food"));
        repository.put(2, new Category(2, "Salary"));
        repository.put(3, new Category(3, "Health"));
        repository.put(4, new Category(4, "Education"));
        repository.put(5, new Category(5, "Transport"));
    }

    @Override
    public void save(Integer id, Category category) {
        repository.put(id, category);
    }

    @Override
    public Collection<Category> findAll() {
        return repository.values();
    }

    public Category findByLabel(String label) {
        return repository.values().stream().filter(
                category -> label.equals(category.getLabel())).findFirst().orElse(null);
    }

    public Category findById(Integer id) {
        return repository.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.remove(id);
    }

}
