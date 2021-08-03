package com.ipsoflatus.dreamgifts.modelo.tabla.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDestinoTableModel extends VentaTableModel {
    
    private EstadoVenta despacho;

    public ListaDestinoTableModel(ObservableService service) {
        super();
        despacho = obtenerEstado();
        columnNames = new String[] {"N° Pedido", "Pack", "Destinarario", "Direccón", "Comuna", "Fecha Entrega", "Horario Entrega"};
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
            return venta.getHoraEntregaInicial() + " a " + venta.getHoraEntregaFinal();
        return null;
    }

    @Override
    public void actualizar(List<Venta> items) {
        if (despacho == null) {
            despacho = obtenerEstado();
        }
        ventas = items.stream().filter(v -> v.getEstadoVenta().equals(despacho)).collect(Collectors.toList());
        fireTableDataChanged();
    }

    private EstadoVenta obtenerEstado() {
        return EVService.getInstance().buscar().get(1);
    }
    

}
