package com.ipsoflatus.dreamgifts.modelo.dao;

import java.util.List;

public interface DAO<T> {
    
    List<T> findAll();
    List<T> findByTermLike(String term);
    T findById(int id);
    void save(T t);
    void update(T t);
    
    default void printError(Class<T> typeClass, Exception e) {
        System.out.println("\n" + typeClass.getSimpleName() + " -> " + e.getClass().getSimpleName() + ":\n" + e.getMessage() + "\n");
    }
}
