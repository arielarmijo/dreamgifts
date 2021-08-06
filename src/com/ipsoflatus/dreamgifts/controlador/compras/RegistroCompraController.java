package com.ipsoflatus.dreamgifts.controlador.compras;

import com.ipsoflatus.dreamgifts.modelo.combobox.ArticuloComboBoxModel;
import com.ipsoflatus.dreamgifts.modelo.combobox.OrdenCompraComboBoxModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.vista.compras.RegistroCompraView;
import java.time.LocalDate;
import java.util.List;

public class RegistroCompraController {

    private final RegistroCompraView view;
    private Factura facturaActual;
    
    public RegistroCompraController(RegistroCompraView view) {
        this.view = view;
    }

    public void filtrarProductos() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulos().getSelectedItem();
        ((ArticuloComboBoxModel) view.getCbxArticulos().getModel()).actualizar(ca.getArticulos());
    }

    public void buscarProveedor() {
        Proveedor proveedor = (Proveedor) view.getCbxProveedores().getSelectedItem();
        view.getTxfRut().setText(proveedor.getRut());
        ((OrdenCompraComboBoxModel) view.getCbxOrdenesCompra().getModel()).actualizar(proveedor.getOrdenesCompra());
    }

    public void cancelar() {
        facturaActual = null;
        view.getTxfRut().setText("");
        view.getTxfNumeroFactura().setText("");
        view.getCbxProveedores().setSelectedIndex(0);
        view.getDpFechaRecepcion().setDate(LocalDate.now());
        view.getTxfCodigo().setText("");
        view.getCbxCategoriaArticulos().setSelectedIndex(0);
        //view.getCbxArticulos().setSelectedIndex(0);
        view.getSpnCantidad().setValue(0);
        view.getSpnValorUnitario().setValue(0);
        view.getDpFechaVencimiento().clear();
    }
    
}
