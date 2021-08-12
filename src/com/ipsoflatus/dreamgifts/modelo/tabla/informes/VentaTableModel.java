    package com.ipsoflatus.dreamgifts.modelo.tabla.informes;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VentaTableModel extends AbstractTableModel implements Observer<Venta> {
    
    protected String[] columnNames;
    protected Class[] columnClases;
    protected List<Venta> ventas = new ArrayList<>();
    protected final VentaService ventaSrv;
    
    public VentaTableModel() {
        ventaSrv = VentaService.getInstance();
        ventaSrv.addObserver(this);
        columnNames = new String[]{"N° Pedido", "Rut Cliente", "Nombre Cliente", "Fecha Compra", "Fecha Entrega" , "Monto"};
        columnClases = new Class[]{Integer.class, String.class, String.class, Date.class, Date.class, Integer.class};
    }
    
    public Venta getItem(int row) {
        return ventas.get(row);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = ventas.get(rowIndex);
        if (columnIndex == 0)
            return venta.getId();
        if (columnIndex == 1)
            return venta.getCliente().getRut();
        if (columnIndex == 2)
            return venta.getCliente().getNombre()+ " " + venta.getCliente().getApellido();
        if (columnIndex == 3)
            return venta.getFechaVenta();
        if (columnIndex == 4)
            return venta.getFechaEntrega();
        if (columnIndex == 5)
            return venta.getPack().getCosto();
        return null;
    }

    @Override
    public int getRowCount() {
        return ventas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClases[columnIndex];
    }
    
    @Override
    public void actualizar(List<Venta> items) {
        ventas = items;
        fireTableDataChanged();
    }
    
}
