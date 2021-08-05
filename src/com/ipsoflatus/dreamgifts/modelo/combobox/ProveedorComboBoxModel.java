package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.servicio.ProveedorService;
import java.util.List;
import java.util.stream.Collectors;

public class ProveedorComboBoxModel extends ObserverComboBoxModel<Proveedor> {

    public ProveedorComboBoxModel() {
        super(ProveedorService.getInstance());
    }
    
    @Override
    public void actualizar(List<Proveedor> items) {
        Proveedor seleccioneProveedor = new Proveedor();
        seleccioneProveedor.setRazonSocial("Seleccione proveedor");
        List<Proveedor> proveedoresActivos = items.stream().filter(p -> p.getEstado()).collect(Collectors.toList());
        proveedoresActivos.add(0, seleccioneProveedor);
        updateModel(proveedoresActivos.toArray());
    }
    
}
