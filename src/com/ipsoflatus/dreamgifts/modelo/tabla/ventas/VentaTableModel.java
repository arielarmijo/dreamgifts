package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class VentaTableModel extends AbstractTableModel implements Observer<Venta> {
    
    protected String[] columnNames;
    protected Class[] columnClases;
    protected static List<Venta> ventas;
    
    public VentaTableModel() {
        ventas = new ArrayList<>();
    }
    
    public Venta getItem(int row) {
        return ventas.get(row);
    }

    @Override
    public  abstract Object getValueAt(int rowIndex, int columnIndex);

    @Override
    public int getRowCount() {
        return ventas.size();
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
    public void actualizar(List<Venta> items) {
        ventas = items;
        fireTableDataChanged();
    }
    
}
