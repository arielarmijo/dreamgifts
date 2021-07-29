package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class BancoComboBoxModel extends ObserverComboBoxModel<Banco> {

    public BancoComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<Banco> items) {
        List<Banco> bancos = items.stream().filter(b -> b.isEstado()).collect(Collectors.toList());
        Banco seleccioneBanco = new Banco("Seleccione banco");
        bancos.add(0, seleccioneBanco);
        updateModel(bancos.toArray());
    }

}
