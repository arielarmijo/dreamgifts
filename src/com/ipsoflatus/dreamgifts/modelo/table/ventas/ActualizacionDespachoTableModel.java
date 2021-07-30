package com.ipsoflatus.dreamgifts.modelo.table.ventas;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public final class ActualizacionDespachoTableModel extends VentaTableModel {

    private final ComunaService comunaSrv = ComunaService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final EVService evSrv = EVService.getInstance();
    private final VentaService ventaSrv = VentaService.getInstance();
    private static List<Comuna> comunas;
    private static List<Pack> packs;
    private static List<EstadoVenta> eevv;
    
    public ActualizacionDespachoTableModel(ObservableService service) {
        super();
        ventaSrv.addObserver(this);
        columnNames = new String[]{"N° Pedido", "Pack", "Destinarario", "Comuna", "Fecha Entrega", "Horario Entrega" , "Estado Entrega"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Date.class, String.class, String.class};
        actualizar();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        if (columnIndex == 0)
            return venta.getId();
        if (columnIndex == 1)
            return packs.get(rowIndex).getNombre();
        if (columnIndex == 2)
            return venta.getNombreDestinatario() + " " + venta.getApellidoDestinatario();
        if (columnIndex == 3)
            return comunas.get(rowIndex).getNombre();
        if (columnIndex == 4)
            return venta.getFechaEntrega();
        if (columnIndex == 5)
            return venta.getHoraEntregaInicial() + " a " + venta.getHoraEntregaFinal();
        if (columnIndex == 6)
            return eevv.get(rowIndex).getNombre();
        return null;
    }

    @Override
    public void actualizar() {
        comunas = ventas.stream().map(v -> comunaSrv.buscar(v.getComunaId())).collect(Collectors.toList());
        packs = ventas.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
        eevv = ventas.stream().map(v -> evSrv.buscar(v.getEstadoVentaId())).collect(Collectors.toList());
    }

}
