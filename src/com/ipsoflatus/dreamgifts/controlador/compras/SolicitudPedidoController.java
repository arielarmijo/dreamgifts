package com.ipsoflatus.dreamgifts.controlador.compras;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.lista.ArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.lista.OrdenCompraDetalleListModel;
import com.ipsoflatus.dreamgifts.modelo.servicio.OrdenCompraService;
import com.ipsoflatus.dreamgifts.vista.compras.SolicitudPedidoView;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class SolicitudPedidoController {

    private final OrdenCompraService ocSrv;
    private final SolicitudPedidoView view;
    private final ArticuloListModel articuloListModel;
    private final OrdenCompraDetalleListModel ocdListModel;
    private OrdenCompra ocActual;
    
    public SolicitudPedidoController(SolicitudPedidoView view) {
        this.view = view;
        this.articuloListModel = (ArticuloListModel) view.getLstArticulos().getModel();
        this.ocdListModel = (OrdenCompraDetalleListModel) view.getLstDetalleOC().getModel();
        this.ocSrv = OrdenCompraService.getInstance();
    }
    
    public void filtrarArticulo() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulos().getSelectedItem();
        if (ca != null) {
            System.out.println(ca + ": " + ca.getArticulos());
            articuloListModel.actualizar(ca.getArticulos());
        }
    }

    public void agregarArticuloOC() {
        Articulo articulo = view.getLstArticulos().getSelectedValue();
        if (articulo == null)
            return;
        OrdenCompraDetalle ocd = new OrdenCompraDetalle();
        if (ocActual != null)
            ocd.setOrdenCompra(ocActual);
        ocd.setArticulo(articulo);
        Integer cantidad = (Integer) view.getSpnCantidad().getValue();
        ocd.setCantidad(cantidad);
        System.out.println("agregar " + ocd);
        ocdListModel.addItem(ocd);
        view.getSpnCantidad().setValue(1);
    }

    public void removerArticuloOC() {
        OrdenCompraDetalle ocd = view.getLstDetalleOC().getSelectedValue();
        if (ocd == null)
            return;
        System.out.println("remover " + ocd);
        ocdListModel.removeItem(ocd);
    }

    public void cancelar() {
        view.getCbxCategoriaArticulos().setSelectedIndex(0);
        view.getCbxProveedores().setSelectedIndex(0);
        view.getLstArticulos().clearSelection();
        view.getLstDetalleOC().clearSelection();
        view.getDpFechaPedido().setDate(LocalDate.now());
        view.getSpnCantidad().setValue(1);
    }
    
    public void grabar() {
        
        List<OrdenCompraDetalle> articulos = ocdListModel.getItems();
        System.out.println(articulos);
        if (articulos.isEmpty()) {
            mostrarInformacion("La solicitud de pedido debe tener artículos.");
            return;
        }
        
        Proveedor proveedor = (Proveedor) view.getCbxProveedores().getSelectedItem();
        if (proveedor.getId() == null) {
            mostrarInformacion("Seleccione proveedor.");
            return;
        }
        
        Date fechaEntrega = Date.valueOf(view.getDpFechaPedido().getDate());
        
        try {
            if (ocActual == null) {
                OrdenCompra oc = new OrdenCompra();
                articulos.forEach(a -> {
                    a.setOrdenCompra(oc);
                });
                oc.setArticulos(articulos);
                oc.setFechaOrden(fechaEntrega);
                oc.setProveedor(proveedor);
                ocSrv.guardar(oc);
            } else {
                
            }
        } catch(Exception e) {
            e.printStackTrace();
            mostrarError(e.getClass().getSimpleName());
        }
        
    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
