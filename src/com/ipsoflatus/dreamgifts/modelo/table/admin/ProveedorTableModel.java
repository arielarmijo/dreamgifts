package com.ipsoflatus.dreamgifts.modelo.table.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import com.ipsoflatus.dreamgifts.modelo.table.ObservableTableModel;

public class ProveedorTableModel extends ObservableTableModel<Proveedor> {
    
    private final ComunaService comunaService = ComunaService.getInstance();
    
    public ProveedorTableModel(ObservableService service) {
        super(service);
        columnNames = new String[] {"Rut", "Razón social", "Contacto", "Dirección", "Comuna", "Teléfono", "Email", "Estado", "Selección"};
        columnClases = new Class[] {String.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, Boolean.class};
        isEditable = new boolean[] {false, false, false, false, false, false, false, false, true};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor item = items.get(rowIndex);
        if (columnIndex == 0)
            return item.getRut();
        if (columnIndex == 1)
            return item.getRazonSocial();
        if (columnIndex == 2)
            return item.getContacto();
        if (columnIndex == 3)
            return item.getDireccion();
        if (columnIndex == 4) {
            Comuna comuna = comunaService.buscar(item.getComunaId());
            return comuna.getNombre();
        }
        if (columnIndex == 5)
            return item.getTelefono();
        if (columnIndex == 6)
            return item.getEmail();
        if (columnIndex == 7)
            return item.getEstado() ? "Activo" : "Inactivo";
        if (columnIndex == 8)
            return seleccionados.get(rowIndex);
        return null;
    }
    
}
