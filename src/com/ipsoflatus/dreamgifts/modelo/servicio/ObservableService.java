package com.ipsoflatus.dreamgifts.modelo.servicio;

public interface ObservableService<T> {
    
    void addObserver(T o);
    void notifyObservers();
}
