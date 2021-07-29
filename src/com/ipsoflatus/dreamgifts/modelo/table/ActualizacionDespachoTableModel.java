package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import java.sql.Date;

public class ActualizacionDespachoTableModel extends ObservableTableModel<Venta> {

    private final ComunaService comunaSrv = ComunaService.getInstance();
    private final EVService evSrv = EVService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    
    public ActualizacionDespachoTableModel(ObservableService service) {
        super(service);
        columnNames = new String[]{"N° Pedido", "Pack", "Destinarario", "Comuna", "Fecha Entrega", "Horario Entrega" , "Estado Entrega"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Date.class, String.class, String.class};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = items.get(rowIndex);
        Comuna comuna = comunaSrv.buscar(item.getComunaId());
        EstadoVenta ev = evSrv.buscar(item.getEstadoVentaId());
        Pack pack = packSrv.buscar(item.getPackId());
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return pack.getNombre();
        if (columnIndex == 2)
            return item.getNombreDestinatario() + " " + item.getApellidoDestinatario();
        if (columnIndex == 3)
            return comuna.getNombre();
        if (columnIndex == 4)
            return item.getFechaEntrega();
        if (columnIndex == 5)
            return item.getHoraEntregaInicial() + " a " + item.getHoraEntregaFinal();
        if (columnIndex == 6)
            return ev.getNombre();
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
