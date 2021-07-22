package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import javax.swing.DefaultComboBoxModel;

public abstract class ObserverComboBoxModel<T> extends DefaultComboBoxModel<T> implements Observer<T> {

    private final ObservableService service;
    
    public ObserverComboBoxModel(ObservableService service) {
        super();
        this.service = service;
        this.service.addObserver(this);
    }
    
    public void updateModel(Object[] items) {
        removeAllElements();
        for (Object item : items) {
            addElement((T) item);
        }
    }
    
}
