package com.ipsoflatus.dreamgifts.modelo.table.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class ConfirmacionPagoTableModel extends VentaTableModel {
    
    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    private final VentaService ventaSrv = VentaService.getInstance();
    private static List<Cliente> clientes;
    private static List<Pack> packs;

    public ConfirmacionPagoTableModel() {
        super();
        ventaSrv.addObserver(this);
        columnNames = new String[]{"N° Pedido", "Nombre Cliente", "Celular", "Pack", "Monto", "Fecha Venta"};
        columnClases = new Class[]{Integer.class, String.class, String.class, String.class, Integer.class, Date.class};
        actualizar();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = ventas.get(rowIndex);
        if (columnIndex == 0) {
            return item.getId();
        }
        if (columnIndex == 1) {
            return clientes.get(rowIndex).getNombre() + " " + clientes.get(rowIndex).getApellido();
        }
        if (columnIndex == 2) {
            return clientes.get(rowIndex).getCelular();
        }
        if (columnIndex == 3) {
            return packs.get(rowIndex).getNombre();
        }
        if (columnIndex == 4) {
            return item.getTotal();
        }
        if (columnIndex == 5) {
            return item.getFechaVenta();
        }
        return null;
    }

    @Override
    public void actualizar() {
        clientes = ventas.stream().map(v -> clienteSrv.buscar(v.getClienteId())).collect(Collectors.toList());
        packs = ventas.stream().map(v -> packSrv.buscar(v.getPackId())).collect(Collectors.toList());
    }

}
