package com.ipsoflatus.dreamgifts.modelo.table;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import java.sql.Date;

public class ConfirmacionPagoTableModel extends ObservableTableModel<Venta> {

    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final PackService packSrv = PackService.getInstance();
    
    public ConfirmacionPagoTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"N° Pedido", "Nombre Cliente", "Celular", "Pack", "Monto", "Fecha Venta"};
        columnClases = new Class[] {Integer.class, String.class, String.class, String.class, Integer.class, Date.class};
        isEditable = new boolean[] {false, false, false, false, false, false};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta item = items.get(rowIndex);
        Cliente cliente = clienteSrv.buscar(item.getClienteId());
        Pack pack = packSrv.buscar(item.getPackId());
        if (columnIndex == 0)
            return item.getId();
        if (columnIndex == 1)
            return cliente.getNombre() + " " + cliente.getApellido();
        if (columnIndex == 2)
            return cliente.getCelular();



        return null;
    }

}
