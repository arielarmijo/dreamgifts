package com.ipsoflatus.dreamgifts.modelo;

import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.servicio.ObservableService;
import java.util.List;

public class ComunaComboBoxModel extends ObserverComboBoxModel<Comuna> {

    public ComunaComboBoxModel(ObservableService service) {
        super(service);
    }
    
    @Override
    public void actualizar(List<Comuna> items) {
        Comuna seleccioneComuna = new Comuna("Seleccione comuna");
        items.add(0, seleccioneComuna);
        updateModel(items.toArray());
    }
    
}
