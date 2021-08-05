package com.ipsoflatus.dreamgifts.modelo.tabla.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.servicio.OrdenCompraService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;
import java.util.Date;

public class DetallePedidoTableModel extends ObservableTableModel<OrdenCompra> {

    public DetallePedidoTableModel() {
        super(OrdenCompraService.getInstance());
        columnNames = new String[] {"N° Pedido", "Proveedor", "Fecha Pedido", "N° Artículos"};
        columnClases = new Class[] {Integer.class, String.class, Date.class, Integer.class,};
        isEditable = new boolean[] {false, false, false, false, false};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrdenCompra item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return item.getProveedor().getRazonSocial();
        if (columnIndex == 2)
            return item.getFechaOrden();
        if (columnIndex == 3)
            return item.getArticulos().stream().mapToInt(a -> a.getCantidad()).sum();
        return null;
    }

}
