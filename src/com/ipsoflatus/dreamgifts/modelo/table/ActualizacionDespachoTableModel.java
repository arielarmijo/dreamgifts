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
import java.util.List;
import java.util.stream.Collectors;

public class ActualizacionDespachoTableModel extends ObservableTableModel<Venta> {

    private final ComunaService comunaSrv = ComunaService.getInstance();
    private final EVService evSrv = EVService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final List<Comuna> comunas;
    private final List<Pack> packs;
    private final List<EstadoVenta> eevv;
    
    public ActualizacionDespachoTableModel(ObservableService service) {
        super(service);
        columnNames = new String[]{"N° Pedido", "Pack", "Destinarario", "Comuna", "Fecha Entrega", "Horario Entrega" , "Estado Entrega"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Date.class, String.class, String.class};
        comunas = items.stream().map(v -> comunaSrv.buscar(v.getComunaId())).collect(Collectors.toList());
        packs = items.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
        eevv = items.stream().map(v -> evSrv.buscar(v.getEstadoVentaId())).collect(Collectors.toList());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return packs.get(rowIndex).getNombre();
        if (columnIndex == 2)
            return item.getNombreDestinatario() + " " + item.getApellidoDestinatario();
        if (columnIndex == 3)
            return comunas.get(rowIndex).getNombre();
        if (columnIndex == 4)
            return item.getFechaEntrega();
        if (columnIndex == 5)
            return item.getHoraEntregaInicial() + " a " + item.getHoraEntregaFinal();
        if (columnIndex == 6)
            return eevv.get(rowIndex).getNombre();
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
