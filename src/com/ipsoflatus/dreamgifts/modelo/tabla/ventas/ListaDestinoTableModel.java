package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.tabla.informes.VentaTableModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDestinoTableModel extends VentaTableModel {
    
    private EstadoVenta despacho;
    DateFormat timeFormat = new SimpleDateFormat("hh:mm");  

    public ListaDestinoTableModel(ObservableService service) {
        super();
        despacho = obtenerEstado();
        columnNames = new String[] {"N° Pedido", "Pack", "Destinatario", "Dirección", "Comuna", "Fecha Entrega", "Horario Entrega"};
        columnClases = new Class[] {Integer.class, String.class, String.class, String.class, String.class, Date.class, String.class};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        if (columnIndex == 0)
            return venta.getId();
        if (columnIndex == 1)
            return venta.getPack().getNombre();
        if (columnIndex == 2)
            return venta.getNombreDestinatario()+ " " + venta.getApellidoDestinatario();
        if (columnIndex == 3)
            return venta.getDireccionDestinatario();
        if (columnIndex == 4)
            return venta.getComuna().getNombre();
        if (columnIndex == 5)
            return venta.getFechaEntrega();
        if (columnIndex == 6)
            return timeFormat.format(venta.getHoraEntregaInicial()) + " a " + timeFormat.format(venta.getHoraEntregaFinal());
        return null;
    }

    @Override
    public void actualizar(List<Venta> items) {
        if (despacho == null) {
            despacho = obtenerEstado();
        }
        if (despacho != null) {
            ventas = items.stream().filter(v -> v.getEstadoVenta().equals(despacho)).collect(Collectors.toList());
            fireTableDataChanged();
        }
    }

    private EstadoVenta obtenerEstado() {
        List<EstadoVenta> result = EVService.getInstance().buscar();
        if (result.size() <= 1)
            return null;
        return EVService.getInstance().buscar().get(1);
    }
    

}
