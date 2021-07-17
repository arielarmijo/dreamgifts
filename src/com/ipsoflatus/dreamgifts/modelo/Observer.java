package com.ipsoflatus.dreamgifts.modelo;

import java.util.List;

public interface Observer<T> {
    
    void actualizar(List<T> items);
    
}
