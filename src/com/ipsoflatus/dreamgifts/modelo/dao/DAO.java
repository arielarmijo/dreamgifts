package com.ipsoflatus.dreamgifts.modelo.dao;

import java.util.List;

public interface DAO<T> {
    
    List<T> findAll();
    List<T> findByTermLike(String term);
    T findById(int id);
    void save(T t);
    void update(T t) throws Exception;
    
}
