package com.ipsoflatus.dreamgifts.modelo;

import com.ipsoflatus.dreamgifts.servicio.ObservableService;
import javax.swing.DefaultComboBoxModel;

public abstract class ObserverComboBoxModel<T> extends DefaultComboBoxModel<T> implements Observer<T> {

    private final ObservableService service;
    
    public ObserverComboBoxModel(ObservableService service) {
        super();
        this.service = service;
        this.service.addObserver(this);
        this.service.notifyObservers();
    }
    
    public void updateModel(Object[] items) {
        removeAllElements();
        for (Object item : items) {
            addElement((T) item);
        }
    }
    
}
