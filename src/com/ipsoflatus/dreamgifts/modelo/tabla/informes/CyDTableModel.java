package com.ipsoflatus.dreamgifts.modelo.tabla.informes;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ventas.ActualizacionDespachoTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class CyDTableModel extends ActualizacionDespachoTableModel {

    @Override
    public void actualizar(List<Venta> items) {
        EstadoVenta devolucion = obtenerEstado();
        if (devolucion != null) {
            ventas = items.stream().filter(v -> v.getEstadoVenta().equals(devolucion)).collect(Collectors.toList());
            fireTableDataChanged();
        }

    }

    private EstadoVenta obtenerEstado() {
        List<EstadoVenta> result = EVService.getInstance().buscar();
        if (result.isEmpty()) {
            return null;
        }
        return EVService.getInstance().buscar().get(3);
    }

}
