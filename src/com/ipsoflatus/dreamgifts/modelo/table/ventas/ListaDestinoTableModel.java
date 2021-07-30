package com.ipsoflatus.dreamgifts.modelo.table.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDestinoTableModel extends VentaTableModel {
    
    private final ComunaService comunaSrv = ComunaService.getInstance();
    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final VentaService ventaSrv = VentaService.getInstance();
    private static List<Comuna> comunas;
    private static List<Cliente> clientes;
    private static List<Pack> packs;

    public ListaDestinoTableModel(ObservableService service) {
        super();
        ventaSrv.addObserver(this);
        columnNames = new String[] {"N° Pedido", "Pack", "Destinarario", "Direccón", "Comuna", "Fecha Entrega", "Horario Entrega"};
        columnClases = new Class[] {Integer.class, String.class, String.class, String.class, String.class, Date.class, String.class};
        clientes = ventas.stream().map(v -> clienteSrv.buscar(v.getClienteId())).collect(Collectors.toList());
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
            return venta.getNombreDestinatario()+ " " + venta.getApellidoDestinatario();
        if (columnIndex == 3)
            return venta.getDireccionDestinatario();
        if (columnIndex == 4)
            return comunas.get(rowIndex).getNombre();
        if (columnIndex == 5)
            return venta.getFechaEntrega();
        if (columnIndex == 6)
            return venta.getHoraEntregaInicial() + " a " + venta.getHoraEntregaFinal();
        return null;
    }

    @Override
    public void actualizar() {
        clientes = ventas.stream().map(v -> clienteSrv.buscar(v.getClienteId())).collect(Collectors.toList());
        comunas = ventas.stream().map(v -> comunaSrv.buscar(v.getComunaId())).collect(Collectors.toList());
        packs = ventas.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
    }

}
