package com.ipsoflatus.dreamgifts.modelo.tabla.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;

public class DetalleFacturaTableModel extends ObservableTableModel<FacturaDetalle> {

    public DetalleFacturaTableModel() {
        super(FacturaService.getInstance());
        columnNames = new String[] {"Código", "Artículo", "Cantidad", "Valor Unitario"};
        columnClases = new Class[] {String.class, String.class, Integer.class, Integer.class,};
        isEditable = new boolean[] {false, false, false, false, false};
    }
    
    public void addItem(FacturaDetalle facturaDetalle) {
        items.add(facturaDetalle);
        fireTableRowsInserted(items.size(), items.size());
    }
    
    public void removeItem(int row) {
        items.remove(row);  
        fireTableRowsDeleted(row, row);
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
        return null;
    }
}
