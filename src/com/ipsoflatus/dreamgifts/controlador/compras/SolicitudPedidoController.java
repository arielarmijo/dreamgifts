package com.ipsoflatus.dreamgifts.controlador.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.lista.ArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.servicio.ProveedorService;
import com.ipsoflatus.dreamgifts.vista.compras.SolicitudPedidoView;
import java.time.LocalDate;

public class SolicitudPedidoController {

    private final ProveedorService proveedorSrv;
    private final SolicitudPedidoView view;
    private final ArticuloListModel articuloListModel;
    
    public SolicitudPedidoController(SolicitudPedidoView view) {
        this.view = view;
        this.articuloListModel = (ArticuloListModel) view.getLstArticulos().getModel();
        this.proveedorSrv = ProveedorService.getInstance();
    }

    public void agregarArticuloOC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removerArticuloOC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void filtrarArticulo() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulos().getSelectedItem();
        if (ca != null) {
            System.out.println(ca + ": " + ca.getArticulos());
            articuloListModel.actualizar(ca.getArticulos());
        }
    }

    public void cancelar() {
        view.getCbxCategoriaArticulos().setSelectedIndex(0);
        view.getCbxProveedores().setSelectedIndex(0);
        view.getLstArticulos().clearSelection();
        view.getDpFechaPedido().setDate(LocalDate.now());
        view.getSpnCantidad().setValue(1);
    }
    
}
