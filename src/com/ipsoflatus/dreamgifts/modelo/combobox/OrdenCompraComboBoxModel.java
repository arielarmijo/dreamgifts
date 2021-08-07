package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.servicio.OrdenCompraService;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenCompraComboBoxModel extends ObserverComboBoxModel<OrdenCompra> {

    public OrdenCompraComboBoxModel() {
        super(OrdenCompraService.getInstance());
    }
    
    @Override
    public void actualizar(List<OrdenCompra> items) {
        OrdenCompra seleccioneOC = new OrdenCompra();
        List<OrdenCompra> ocActivas = items.stream().filter(p -> true).collect(Collectors.toList());
        ocActivas.add(0, seleccioneOC);
        updateModel(ocActivas.toArray());
    }
    
}
