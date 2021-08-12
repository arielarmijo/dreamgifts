package com.ipsoflatus.dreamgifts.modelo.tabla.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;
import java.util.Date;
import java.util.List;

public class FacturaTableModel extends ObservableTableModel<Factura> {

    public FacturaTableModel() {
        super(FacturaService.getInstance());
        columnNames = new String[] {"N° Factura", "Rut Proveedor", "Proveedor", "Total", "Fecha Recepción"};
        columnClases = new Class[] {Integer.class, String.class, String.class, Integer.class, Date.class};
        isEditable = new boolean[] {false, false, false, false, false};
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factura item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNumero();
        if (columnIndex == 1)
            return item.getOrdenCompra().getProveedor().getRut();
        if (columnIndex == 2)
            return item.getOrdenCompra().getProveedor().getRazonSocial();
        if (columnIndex == 3)
            return item.getArticulos().stream().mapToInt(a -> a.getCantidad() * a.getValorUnitario()).sum();
        if (columnIndex == 4)
            return item.getFecha();
        if (columnIndex == 5)
            return "Editar";
        return null;
    }
    
    public void setItems(List<Factura> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public List<Factura> getItems() {
        return items;
    }
    
}
