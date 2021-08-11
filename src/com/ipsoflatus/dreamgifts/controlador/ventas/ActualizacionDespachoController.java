package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ventas.ActualizacionDespachoTableModel;
import com.ipsoflatus.dreamgifts.vista.ventas.ActualizacionDespachoView;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ActualizacionDespachoController implements TableModelListener {

    private final VentaService ventaSrv;
    private final ActualizacionDespachoView view;
    private final ActualizacionDespachoTableModel tableModel;
    private Venta ventaActual;
    
    public ActualizacionDespachoController(ActualizacionDespachoView view) {
        this.ventaSrv = VentaService.getInstance();
        this.view = view;
        this.tableModel = (ActualizacionDespachoTableModel) view.getjTable().getModel();
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actualizarEstado() {
        if (ventaActual != null) {
            EstadoVenta ev = (EstadoVenta) view.getCbxEstadoVenta().getSelectedItem();
            ventaActual.setEstadoVenta(ev);
            ventaSrv.editar(ventaActual);
        }
    }

    public void seleccionarVenta() {
        int row = view.getjTable().getSelectedRow();
        ventaActual = tableModel.getItem(row);
        view.getTxfNumeroVenta().setText(String.valueOf(ventaActual.getId()));
        view.getCbxEstadoVenta().setSelectedItem(ventaActual.getEstadoVenta());
    }

}
