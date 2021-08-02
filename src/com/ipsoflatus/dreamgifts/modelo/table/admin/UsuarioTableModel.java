package com.ipsoflatus.dreamgifts.modelo.table.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import com.ipsoflatus.dreamgifts.modelo.servicio.UsuarioService;
import com.ipsoflatus.dreamgifts.modelo.table.ObservableTableModel;

public class UsuarioTableModel extends ObservableTableModel<Usuario> {

    public UsuarioTableModel() {
        super(UsuarioService.getInstance());
        columnNames = new String[] {"Nombre", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNombre();
        if (columnIndex == 1)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 2)
            return seleccionados.get(rowIndex);
        return null;
    }
    
}
