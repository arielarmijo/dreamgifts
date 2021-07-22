package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.sql.Date;

public class ArticuloTableModel extends ObservableTableModel<Articulo>{
    
    private final CategoriaArticuloService caService = CategoriaArticuloService.getInstance();

    public ArticuloTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"Nombre", "Marca", "Categoría", "Stock", "Fecha Vencimiento", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, String.class, Integer.class, Date.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNombre();
        if (columnIndex == 1)
            return item.getMarca();
        if (columnIndex == 2) {
            CategoriaArticulo ca = caService.buscar(item.getCategoriaId());
            return ca.getNombre();
        }
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
    
}
