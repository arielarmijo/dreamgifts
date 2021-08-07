/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.tabla.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ObservableTableModel;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ArmPackTableModel extends ObservableTableModel<Pack> {
    
    public ArmPackTableModel() {
        super(PackService.getInstance());
        columnNames = new String[] {"Nombre Pack", "Stock Actual", "Stock Critico", "Fecha Critico Inicio", "Fecha Critico Termino"};
        columnClases = new Class[] {String.class, Integer.class, Integer.class, Date.class, Date.class};
        isEditable = new boolean[] {false, false, false, false, false};
    
}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Pack item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getNombre();
        if (columnIndex == 1)
            return item.getStock();
        if (columnIndex == 2)
            return item.getStockCritico();
        if (columnIndex == 3)
            return item.getFechaInicio();
        if (columnIndex == 4)
            return item.getFechaTermino();
        if (columnIndex == 5)
            return seleccionados.get(rowIndex);
        return null;
    }
    }
    
    
    
    
    
    
    
