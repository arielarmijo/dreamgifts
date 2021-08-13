package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.tabla.informes.VentaTableModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ActualizacionDespachoTableModel extends VentaTableModel {

    DateFormat timeFormat = new SimpleDateFormat("hh:mm");  
    
    public ActualizacionDespachoTableModel() {
        super();
        columnNames = new String[]{"N° Pedido", "Pack", "Destinatario", "Comuna", "Fecha Entrega", "Horario Entrega" , "Estado Venta"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Date.class, String.class, String.class};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        if (columnIndex == 0)
            return venta.getId();
        if (columnIndex == 1)
            return venta.getPack().getNombre();
        if (columnIndex == 2)
            return venta.getNombreDestinatario() + " " + venta.getApellidoDestinatario();
        if (columnIndex == 3)
            return venta.getComuna().getNombre();
        if (columnIndex == 4)
            return venta.getFechaEntrega();
        if (columnIndex == 5)
            return timeFormat.format(venta.getHoraEntregaInicial()) + " a " + timeFormat.format(venta.getHoraEntregaFinal());
        if (columnIndex == 6)
            return venta.getEstadoVenta().getNombre();
        return null;
    }

}
