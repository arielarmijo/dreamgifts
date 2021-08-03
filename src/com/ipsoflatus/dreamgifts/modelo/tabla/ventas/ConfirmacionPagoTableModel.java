package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import java.sql.Date;

public final class ConfirmacionPagoTableModel extends VentaTableModel {
    
    public ConfirmacionPagoTableModel() {
        super();
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

}
