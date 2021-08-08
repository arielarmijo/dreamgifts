package com.ipsoflatus.dreamgifts.modelo.tabla.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ArticuloObserver;
import com.ipsoflatus.dreamgifts.modelo.tabla.CategoriaArticuloObserver;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ArticuloTableModel extends AbstractTableModel implements CategoriaArticuloObserver, ArticuloObserver {
    
    private final String[] columnNames;
    private final Class[] columnClases;
    private final boolean[] isEditable;
    private List<Articulo> items;
    private List<Boolean> seleccionados;
    private final ArticuloService articuloSrv;
    private final CategoriaArticuloService caSrv;
    
    public ArticuloTableModel() {
        items = new ArrayList<>();
        articuloSrv = ArticuloService.getInstance();
        articuloSrv.addObserver(this);
        caSrv = CategoriaArticuloService.getInstance();
        caSrv.addObserver(this);
        columnNames = new String[] {"Nombre", "Marca", "Categoría", "Stock", "Fecha Vencimiento", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, String.class, Integer.class, Date.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, false, false, true};
    }
    
    public Articulo getItem(int row) {
        return items.get(row);
    }
    
    public List<Articulo> getSelected() {
        return items.stream()
                .filter(i -> seleccionados.get(items.indexOf(i)))
                .collect(Collectors.toList());
    }
    
    public void selectAll(boolean select) {
        seleccionados.replaceAll(i -> select);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNombre();
        if (columnIndex == 1)
            return item.getMarca();
        if (columnIndex == 2) 
            return item.getCategoriaArticulo().getNombre();
        if (columnIndex == 3)
            return item.getStock();
        if (columnIndex == 4)
            return item.getFechaVencimiento();
        if (columnIndex == 5)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 6)
            return seleccionados.get(rowIndex);
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
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == columnNames.length - 1)
            seleccionados.set(rowIndex, (boolean) value);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable[columnIndex];
    }

    @Override
    public void actualizarCategoriaArticulo(List<CategoriaArticulo> items) {
        this.items = items.stream().map(ca -> ca.getArticulos()).flatMap(Collection::stream).collect(Collectors.toList());
        seleccionados = new ArrayList<>();
        this.items.forEach(i -> {
            seleccionados.add(Boolean.FALSE);
        });
        fireTableDataChanged();
    }
    
    @Override
    public void actualizarArticulo(List<Articulo> items) {
        this.items = items;
        seleccionados = new ArrayList<>();
        this.items.forEach(i -> {
            seleccionados.add(Boolean.FALSE);
        });
        fireTableDataChanged();
    }

    @Override
    public void actualizar(List items) {
        if (items != null && !items.isEmpty()) {
            Object o = items.get(0);
            if (o instanceof CategoriaArticulo)
                actualizarCategoriaArticulo((List<CategoriaArticulo>) items);
            if (o instanceof Articulo)
                actualizarArticulo((List<Articulo>) items);
        }
    }
    
    
}
