package com.ipsoflatus.dreamgifts.modelo.servicio;

import java.util.List;

public interface Service<T> {
    
    List<T> buscar();
    T buscar(Integer id);
    List<T> buscar(String text);
    void guardar(T t);
    void editar(T t);
    void cambiarEstado(List<Integer> ids, Boolean estado);
    
}
