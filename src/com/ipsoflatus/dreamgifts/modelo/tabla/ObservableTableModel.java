package com.ipsoflatus.dreamgifts.modelo.tabla;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public abstract class ObservableTableModel<T> extends AbstractTableModel implements Observer<T> {

    private final ObservableService service;
    protected String[] columnNames = {"Código", "Nombre", "Estado", "Selección"};
    protected Class[] columnClases = {String.class, String.class, String.class, Boolean.class};
    protected boolean[] isEditable = {false, false, false, true};
    protected List<T> items = new ArrayList<>();
    protected final List<Boolean> seleccionados = new ArrayList<>();

    public ObservableTableModel(ObservableService service) {
        this.service = service;
        this.service.addObserver(this);
    }

    public T getItem(int row) {
        return items.get(row);
    }
    
    public List<T> getSelected() {
        return items.stream()
                .filter(i -> seleccionados.get(items.indexOf(i)))
                .collect(Collectors.toList());
    }
    
    public void selectAll(boolean select) {
        seleccionados.replaceAll(i -> select);
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
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClases[columnIndex];
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == columnNames.length - 1)
            seleccionados.set(rowIndex, (boolean) value);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable[columnIndex];
    }

    @Override
    public void actualizar(List<T> items) {
        this.items = items;
        this.items.forEach(i -> {
            seleccionados.add(Boolean.FALSE);
        });
        fireTableDataChanged();
    }
    
}
