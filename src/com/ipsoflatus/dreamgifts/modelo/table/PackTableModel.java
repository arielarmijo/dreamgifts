package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;

public class PackTableModel extends ObservableTableModel<Pack> {
    
    public PackTableModel() {
        super(PackService.getInstance());
        columnNames = new String[] {"Nombre", "Precio", "Stock", "Estado", "Selección"};
        columnClases = new Class[] {String.class, Integer.class, Integer.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pack item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNombre();
        if (columnIndex == 1)
            return item.getCosto();
        if (columnIndex == 2)
            return item.getStock();
        if (columnIndex == 3)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 4)
            return seleccionados.get(rowIndex);
        return null;
    }

}
