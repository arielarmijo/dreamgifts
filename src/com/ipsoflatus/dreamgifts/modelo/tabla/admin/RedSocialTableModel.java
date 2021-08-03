package com.ipsoflatus.dreamgifts.modelo.tabla.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;

public class RedSocialTableModel extends ObservableTableModel<RedSocial> {

    public RedSocialTableModel(ObservableService service) {
        super(service);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RedSocial item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getCodigo();
        if (columnIndex == 1)
            return item.getNombre();
        if (columnIndex == 2)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 3)
            return seleccionados.get(rowIndex);
        return null;
    }
    
}
