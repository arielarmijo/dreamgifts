package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDestinoTableModel extends ObservableTableModel<Venta> {
    
    private final ComunaService comunaSrv = ComunaService.getInstance();
    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final List<Cliente> clientes;
    private final List<Comuna> comunas;
    private final List<Pack> packs;

    public ListaDestinoTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"N° Pedido", "Pack", "Destinarario", "Direccón", "Comuna", "Fecha Entrega", "Horario Entrega"};
        columnClases = new Class[] {Integer.class, String.class, String.class, String.class, String.class, Date.class, String.class};
        clientes = items.stream().map(v -> clienteSrv.buscar(v.getClienteId())).collect(Collectors.toList());
        comunas = items.stream().map(v -> comunaSrv.buscar(v.getComunaId())).collect(Collectors.toList());
        packs = items.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return packs.get(rowIndex).getNombre();
        if (columnIndex == 2)
            return item.getNombreDestinatario()+ " " + item.getApellidoDestinatario();
        if (columnIndex == 3)
            return item.getDireccionDestinatario();
        if (columnIndex == 4)
            return comunas.get(rowIndex).getNombre();
        if (columnIndex == 5)
            return item.getFechaEntrega();
        if (columnIndex == 6)
            return item.getHoraEntregaInicial() + " a " + item.getHoraEntregaFinal();
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
