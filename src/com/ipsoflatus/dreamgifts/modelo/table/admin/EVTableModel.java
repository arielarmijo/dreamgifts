/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.table.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.table.ObservableTableModel;

/**
 *
 * @author Usuario
 */
public class EVTableModel extends ObservableTableModel<EstadoVenta> {

    public EVTableModel(ObservableService service) {
        super(service);
         columnNames = new String[] {"Codigo", "Nombre", "Descripción", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, String.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EstadoVenta item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getCodigo();
        if (columnIndex == 1)
            return item.getNombre();
        if (columnIndex == 2)
            return item.getDescripcion();
        if (columnIndex == 3)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 4)
            return seleccionados.get(rowIndex);
        return null;
    }
    
}
