package com.ipsoflatus.dreamgifts.modelo;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class CategoriaArticuloTableModel extends AbstractTableModel implements Observer<CategoriaArticulo> {

    private final ObservableService service;
    private final String[] columnNames = {"Código", "Nombre", "Estado", "Selección"};
    private final Class[] columnClases = {String.class, String.class, String.class, Boolean.class};
    private List<CategoriaArticulo> items = new ArrayList<>();
    private final List<Boolean> estados = new ArrayList<>();
    
    public CategoriaArticuloTableModel(ObservableService service) {
        this.service = service;
        this.service.addObserver(this);
    }
    
    public CategoriaArticulo getCategoriaArticulo(int row) {
        return items.get(row);
    }
    
    public List<CategoriaArticulo> getSelected() {
        return items.stream()
                .filter(i -> estados.get(items.indexOf(i)))
                .collect(Collectors.toList());
    }
    
    public void selectAll(boolean select) {
        estados.replaceAll(i -> select);
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return items.size();    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoriaArticulo item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getCodigo();
        if (columnIndex == 1)
            return item.getNombre();
        if (columnIndex == 2)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 3)
            return estados.get(rowIndex);
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 3)
            estados.set(rowIndex, (boolean) value);
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
        return columnIndex == 3;
    }

    @Override
    public void actualizar(List<CategoriaArticulo> items) {
        this.items = items;
        this.items.forEach(i -> {
            estados.add(Boolean.FALSE);
        });
        fireTableDataChanged();
    }
    
}
