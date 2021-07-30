package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConfirmacionPagoTableModel extends ObservableTableModel<Venta> {

    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final List<Cliente> clientes;
    private final List<Pack> packs;
    
    public ConfirmacionPagoTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"N° Pedido", "Nombre Cliente", "Celular", "Pack", "Monto", "Fecha Venta"};
        columnClases = new Class[] {Integer.class, String.class, String.class, String.class, Integer.class, Date.class};
        isEditable = new boolean[] {false, false, false, false, false, false};
        clientes = items.stream().map(v -> clienteSrv.buscar(v.getClienteId())).collect(Collectors.toList());
        packs = items.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return clientes.get(rowIndex).getNombre() + " " + clientes.get(rowIndex).getApellido();   
        if (columnIndex == 2)
            return clientes.get(rowIndex).getCelular();
        if (columnIndex == 3)
            return packs.get(rowIndex).getNombre();
        if (columnIndex == 4)
            return item.getTotal();
        if (columnIndex == 5)
            return item.getFechaVenta();
        return null;
    }

}
