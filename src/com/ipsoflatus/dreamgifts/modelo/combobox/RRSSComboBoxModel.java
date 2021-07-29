package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class RRSSComboBoxModel extends ObserverComboBoxModel<RedSocial> {

    public RRSSComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<RedSocial> items) {
        List<RedSocial> rrssActivas = items.stream().filter(rs -> rs.isEstado()).collect(Collectors.toList());
        RedSocial seleccioneRedSocial = new RedSocial("Seleccione red social");
        rrssActivas.add(0, seleccioneRedSocial);
        updateModel(rrssActivas.toArray());
    }

}
