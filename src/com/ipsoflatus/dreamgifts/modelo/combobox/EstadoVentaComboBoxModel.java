package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import java.util.List;
import java.util.stream.Collectors;

public class EstadoVentaComboBoxModel extends ObserverComboBoxModel<EstadoVenta> {

    public EstadoVentaComboBoxModel() {
        super(EVService.getInstance());
    }

    @Override
    public void actualizar(List<EstadoVenta> items) {
        EstadoVenta seleccioneEstadoVenta = new EstadoVenta();
        seleccioneEstadoVenta.setNombre("Seleccione estado");
        List<EstadoVenta> estadosActivos = items.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        estadosActivos.add(0, seleccioneEstadoVenta);
        updateModel(estadosActivos.toArray());
    }

}
