/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.table.ObservableTableModel;

/**
 *
 * @author Usuario
 */
public class BancoTableModel  extends ObservableTableModel<Banco> {

    public BancoTableModel(ObservableService service) {
        super(service);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Banco item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getCodigo();
        if (columnIndex == 1)
            return item.getNombre();
        if (columnIndex == 2)
            return item.isEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 3)
            return seleccionados.get(rowIndex);
        return null;
    }
       
}
