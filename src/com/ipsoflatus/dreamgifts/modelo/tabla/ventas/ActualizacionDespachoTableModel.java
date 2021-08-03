package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.sql.Date;
import java.util.stream.Collectors;

public final class ActualizacionDespachoTableModel extends VentaTableModel {

    public ActualizacionDespachoTableModel(ObservableService service) {
        super();
        columnNames = new String[]{"N° Pedido", "Pack", "Destinarario", "Comuna", "Fecha Entrega", "Horario Entrega" , "Estado Entrega"};
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
            return venta.getHoraEntregaInicial() + " a " + venta.getHoraEntregaFinal();
        if (columnIndex == 6)
            return venta.getEstadoVenta().getNombre();
        return null;
    }

}
