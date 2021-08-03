package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import java.util.List;

public interface DAO<T> {
    
    List<T> findAll();
    List<T> findByTermLike(String term);
    T findById(int id);
    void save(T t);
    void update(T t);
    void updateStateByIds(List<Integer> ids, boolean estado);
    
}
