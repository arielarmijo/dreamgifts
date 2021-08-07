package com.ipsoflatus.dreamgifts.modelo.tabla.informes;

import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import java.util.Date;

public class ClienteTableModel extends VentaTableModel {
       
    public ClienteTableModel() {
        super();
        columnNames = new String[] {"RUT", "Nombre Cliente", "Fecha Venta", "Fecha Pago", "Fecha Entrega", "Comuna Entrega", "Pack", "Costo Pack", "Estado Pack"};
        columnClases = new Class[] {String.class, String.class, Date.class, Date.class, Date.class, String.class, String.class, Integer.class, String.class};
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = ventas.get(rowIndex);
        if (columnIndex == 0)
            return item.getCliente().getRut();
        if (columnIndex == 1)
            return item.getCliente().getNombre() + " " + item.getCliente().getApellido();
        if (columnIndex == 2)
            return item.getFechaVenta();
        if (columnIndex == 3)
            return item.getFechaTransferencia();
        if (columnIndex == 4)
            return item.getFechaEntrega();
        if (columnIndex == 5)
            return item.getComuna();
        if (columnIndex == 6)
            return item.getPack();
        if (columnIndex == 7)
            return item.getPack().getCosto();
        if (columnIndex == 8)
            return item.getEstadoVenta().getNombre();
        return null;
    }
    
}
