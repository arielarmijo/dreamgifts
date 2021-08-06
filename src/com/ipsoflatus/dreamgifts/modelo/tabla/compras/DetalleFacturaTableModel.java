package com.ipsoflatus.dreamgifts.modelo.tabla.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DetalleFacturaTableModel extends AbstractTableModel {

    private final String[] columnNames;
    private final Class[] columnClases;
    private List<FacturaDetalle> items;
    
    public DetalleFacturaTableModel() {
        this.columnNames = new String[] {"Código", "Artículo", "Cantidad", "Valor Unitario", "Fecha Vencimiento"};
        this.columnClases = new Class[] {String.class, String.class, Integer.class, Integer.class, Date.class};
        this.items  = new ArrayList<>();
    }
    
    public void clearItems() {
        items.clear();
        fireTableDataChanged();
    }
    
    public List<FacturaDetalle> getItems() {
        return items;
    }
    
    public void setItems(List<FacturaDetalle> items) {
        this.items = items;
        fireTableDataChanged();
    }
    
    public FacturaDetalle getItem(int row) {
        return items.get(row);
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

    @Override
    public int getRowCount() {
        return items.size();  
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClases[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
