package io.github.denkoch.mycosts.repository;

import io.github.denkoch.mycosts.entities.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryRepository implements ObjectRepository<Category> {

    private SortedMap<Integer, Category> repository = new TreeMap<>();

    public CategoryRepository() {
        repository.put(1, new Category(1, "Food", 1));
        repository.put(2, new Category(2, "Salary",1));
        repository.put(3, new Category(3, "Health",2));
        repository.put(4, new Category(4, "Education",2));
        repository.put(5, new Category(5, "Transport",3));
    }

    @Override
    public void save(Integer id, Category category) {
        repository.put(id, category);
    }

    @Override
    public Collection<Category> findAllByUserId(Integer id){
        return repository.values().stream().filter(category -> id.equals(category.getUserId())).collect(Collectors.toList());
    }

    public Category findById(Integer id) {
        return repository.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.remove(id);
    }

    public Integer maxId(){
        return repository.lastKey();
    }
}
