package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class ComunaComboBoxModel extends ObserverComboBoxModel<Comuna> {

    public ComunaComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<Comuna> items) {
        List<Comuna> comunasActivas = items.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        Comuna seleccioneComuna = new Comuna();
        seleccioneComuna.setNombre("Seleccione comuna");
        comunasActivas.add(0, seleccioneComuna);
        updateModel(comunasActivas.toArray());
    }

}
