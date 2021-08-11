package com.ipsoflatus.dreamgifts.modelo.tabla.informes;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class InventarioTableModel extends AbstractTableModel implements Observer<Factura> {
    
    private final FacturaService facturaSrv;
    private final String[] columnNames;
    private final Class[] columnClases;
    private List<FacturaDetalle> items;

    public InventarioTableModel() {
        facturaSrv = FacturaService.getInstance();
        facturaSrv.addObserver(this);
        columnNames = new String[]{"Código", "Artículo", "Categoría", "Stock", "Valor Unitario", "Fecha Vencimiento", "Proveedor"};
        columnClases = new Class[]{String.class, String.class, String.class, Integer.class, Integer.class, Date.class, String.class};
    }
    
    public FacturaDetalle getItem(int row) {
        return items.get(row);
    }
    
    public void setItems(List<FacturaDetalle> items) {
        this.items = items;
        fireTableDataChanged();
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        FacturaDetalle item = items.get(row);
        if (column == 0)
            return item.getCodigo();
        if (column == 1)
            return item.getArticulo().getNombre();
        if (column == 2)
            return item.getArticulo().getCategoriaArticulo().getNombre();
        if (column == 3)
            return item.getCantidad();
        if (column == 4)
            return item.getValorUnitario();
        if (column == 5)
            return item.getFechaVencimiento();
        if (column == 6)
            return item.getFactura().getOrdenCompra().getProveedor().getRazonSocial();
        return null;
    }

    @Override
    public int getRowCount() {
        return items.size();
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
    public void actualizar(List<Factura> items) {
        this.items = items.stream().flatMap(f -> f.getArticulos().stream()).collect(Collectors.toList());
        fireTableDataChanged();
    }

}
