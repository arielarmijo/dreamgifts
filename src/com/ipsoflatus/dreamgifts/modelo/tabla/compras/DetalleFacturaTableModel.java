package com.ipsoflatus.dreamgifts.modelo.tabla.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;
import java.util.Date;
import java.util.List;

public class DetalleFacturaTableModel extends ObservableTableModel<FacturaDetalle> {

    public DetalleFacturaTableModel() {
        super(FacturaService.getInstance());
        columnNames = new String[] {"Código", "Artículo", "Cantidad", "Valor Unitario", "Fecha Vencimiento"};
        columnClases = new Class[] {String.class, String.class, Integer.class, Integer.class, Date.class};
        isEditable = new boolean[] {false, false, false, false, false};
    }
    
    public List<FacturaDetalle> getItems() {
        return items;
    }
    
    public void addItem(FacturaDetalle facturaDetalle) {
        items.add(facturaDetalle);
        fireTableRowsInserted(items.size(), items.size());
    }
    
    public void removeItem(int row) {
        items.remove(row);  
        fireTableRowsDeleted(row, row);
    }
    
    public void updateItem(int row, FacturaDetalle articulo) {
        System.out.println(row);
        System.out.println(items);
        items.set(row, articulo);
        System.out.println(items);
        fireTableRowsUpdated(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FacturaDetalle item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getCodigo();
        if (columnIndex == 1)
            return item.getArticulo().getNombre();
        if (columnIndex == 2)
            return item.getCantidad();
        if (columnIndex == 3)
            return item.getValorUnitario();
        if (columnIndex == 4)
            return item.getFechaVencimiento();
        return null;
    }
}
