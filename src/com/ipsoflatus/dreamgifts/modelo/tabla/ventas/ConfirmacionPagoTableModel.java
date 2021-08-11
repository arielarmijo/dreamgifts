package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.tabla.informes.VentaTableModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class ConfirmacionPagoTableModel extends VentaTableModel {
   
    private EstadoVenta pendiente;
    
    public ConfirmacionPagoTableModel() {
        super();
        pendiente = obtenerEstado();
        columnNames = new String[]{"N° Pedido", "Nombre Cliente", "Celular", "Pack", "Monto", "Fecha Venta"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Integer.class, Date.class};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        if (columnIndex == 0) {
            return venta.getId();
        }
        if (columnIndex == 1) {
            return venta.getCliente().getNombre() + " " + venta.getCliente().getApellido();
        }
        if (columnIndex == 2) {
            return venta.getCliente().getCelular();
        }
        if (columnIndex == 3) {
            return venta.getPack().getNombre();
        }
        if (columnIndex == 4) {
            return venta.getTotal();
        }
        if (columnIndex == 5) {
            return venta.getFechaVenta();
        }
        return null;
    }

    @Override
    public void actualizar(List<Venta> items) {
        if (pendiente == null) {
            pendiente = obtenerEstado();
        }
        if (pendiente != null) {
            ventas = items.stream().filter(v -> v.getEstadoVenta().equals(pendiente)).collect(Collectors.toList());
            fireTableDataChanged();
        } 
    }

    private EstadoVenta obtenerEstado() {
        List<EstadoVenta> result = EVService.getInstance().buscar();
        if (result.isEmpty())
            return null;
        return EVService.getInstance().buscar().get(0);
    }

}
