package com.ipsoflatus.dreamgifts.modelo.servicio;

import java.util.List;

public interface Service<T> {
    
    List<T> buscar();
    T buscar(String text);
    void guardar(T t);
    void editar(Integer id, T t);
    
}
