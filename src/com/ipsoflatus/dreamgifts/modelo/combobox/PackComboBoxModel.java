package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class PackComboBoxModel extends ObserverComboBoxModel<Pack> {

    public PackComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<Pack> items) {
        List<Pack> packs = items.stream().filter(p -> p.getEstado()).collect(Collectors.toList());
        Pack seleccionePack = new Pack();
        seleccionePack.setNombre("Seleccione pack");
        packs.add(0, seleccionePack);
        updateModel(packs.toArray());
    }

}
