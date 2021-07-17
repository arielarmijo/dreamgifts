package com.ipsoflatus.dreamgifts.servicio;

public interface ObservableService<T> {
    
    void addObserver(T o);
    void notifyObservers();
}
