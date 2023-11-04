package io.github.denkoch.mycosts.repository;

public interface ObjectRepository<T> {

    public void save(Integer i, T t);

    public Iterable<T> findAll();

    public void deleteById(Integer i);

}