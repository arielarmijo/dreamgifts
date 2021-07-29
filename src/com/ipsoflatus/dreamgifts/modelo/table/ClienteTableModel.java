/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class ClienteTableModel extends ObservableTableModel<Cliente> {

    public ClienteTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"Rut", "Nombre", "Apellido", "Email", "Celular", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
 Cliente item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getRut();
        if (columnIndex == 1)
            return item.getNombre();
        if (columnIndex == 2)
            return item.getApellido();
        if (columnIndex == 3)
            return item.getCorreo();
        if (columnIndex == 4)
            return item.getCelular();
        if (columnIndex == 5)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 6)
            return seleccionados.get(rowIndex);
        return null;
    }
    
}