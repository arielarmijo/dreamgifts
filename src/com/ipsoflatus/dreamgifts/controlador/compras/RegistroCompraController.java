package com.ipsoflatus.dreamgifts.controlador.compras;

import com.ipsoflatus.dreamgifts.modelo.combobox.ArticuloComboBoxModel;
import com.ipsoflatus.dreamgifts.modelo.combobox.OrdenCompraComboBoxModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.compras.DetalleFacturaTableModel;
import com.ipsoflatus.dreamgifts.vista.compras.RegistroCompraView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class RegistroCompraController {

    private final FacturaService facturaSrv;
    private final RegistroCompraView view;
    private final DetalleFacturaTableModel tableModel;
    private Factura facturaActual;
    private FacturaDetalle articuloActual;
    
    public RegistroCompraController(RegistroCompraView view) {
        this.facturaSrv = FacturaService.getInstance();
        this.view = view;
        this.tableModel = (DetalleFacturaTableModel) view.getjTable().getModel();
    }

    public void buscarFactura() {
        try {
            Integer id = Integer.valueOf(view.getTxfNumeroFactura().getText());
            facturaActual = facturaSrv.buscarPorId(id);
            System.out.println(facturaActual);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            mostrarInformacion("Ingrese un número.");
            view.getTxfNumeroFactura().setText("");
        } catch(Exception e) {
            e.printStackTrace();
            mostrarError(e.getMessage());
        }
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
        limpiarFactura();
        limpiarArticulo();
        tableModel.actualizar(new ArrayList<>());
    }
    
    public void grabar() {
        
        int numero = -1;
        try {
            numero = Integer.valueOf(view.getTxfNumeroFactura().getText());
        } catch(NumberFormatException e) {
            mostrarInformacion("Ingrese un número.");
        }
        
        OrdenCompra oc = (OrdenCompra) view.getCbxOrdenesCompra().getSelectedItem();
        if (oc.getId() == null) {
            mostrarInformacion("Seleccione orden de compra.");
            return;
        }
        LocalDate fecha = view.getDpFechaRecepcion().getDate();
        Date fechaRecepcion = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        List<FacturaDetalle> articulos = tableModel.getItems();
        if (articulos.isEmpty()) {
            mostrarInformacion("Agregue artículos a la factura.");
            return;
        }
        
        try {
            if (facturaActual == null) {
                Factura factura = new Factura();
                factura.setNumero(numero);
                factura.setFecha(fechaRecepcion);
                factura.setOrdenCompra(oc);
                factura.setArticulos(articulos);
                facturaSrv.guardar(factura);
            }
        } catch(Exception e) {
            e.printStackTrace();
            mostrarError(e.getMessage());
        }
        
    }
    
    public void agregarArticulo() {
        
        String codigo = view.getTxfCodigo().getText();
        if (codigo.isEmpty()) {
            mostrarInformacion("Ingrese código.");
            return;
        }
        
        Articulo articulo = (Articulo) view.getCbxArticulos().getSelectedItem();
        if (articulo.getId() == null) {
            mostrarInformacion("Seleccione artículo.");
            return;
        }
        
        Integer cantidad = (Integer) view.getSpnCantidad().getValue();
        Integer valorUnitario = (Integer) view.getSpnValorUnitario().getValue();
        
        FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setCodigo(codigo);
        facturaDetalle.setArticulo(articulo);
        facturaDetalle.setCantidad(cantidad);
        facturaDetalle.setValorUnitario(valorUnitario);
        
        LocalDate fecha = view.getDpFechaVencimiento().getDate();
        System.out.println("fecha vencimiento: " + fecha);
        if (fecha != null) {
            Date fechaVencimiento = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
            facturaDetalle.setFechaVencimiento(fechaVencimiento);
        }
        
        if (articuloActual == null) {
            tableModel.addItem(facturaDetalle);
        }
        else {
            int row = view.getjTable().getSelectedRow();
            if (row != -1)
                tableModel.updateItem(row, facturaDetalle);
        }
            
        limpiarArticulo();
    }
    
    public void quitarArticulo() {
        int row = view.getjTable().getSelectedRow();
        if (row != -1) {
            tableModel.removeItem(row);
            limpiarArticulo();
        }
    }
    
    public void seleccionarArticulo() {
        int row = view.getjTable().getSelectedRow();
        articuloActual = tableModel.getItem(row);
        System.out.println("articulo seleccionado: " + row + " -> " + articuloActual);
        view.getTxfCodigo().setText(articuloActual.getCodigo());
        view.getCbxArticulos().setSelectedItem(articuloActual.getArticulo());
        view.getSpnCantidad().setValue(articuloActual.getCantidad());
        view.getSpnValorUnitario().setValue(articuloActual.getValorUnitario());
        Date date = articuloActual.getFechaVencimiento();
        if (date != null) {
            LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            view.getDpFechaVencimiento().setDate(localDate);
        } else {
            view.getDpFechaVencimiento().clear();
        }
    }
    
    private void limpiarFactura() {
        facturaActual = null;
        view.getTxfRut().setText("");
        view.getTxfNumeroFactura().setText("");
        view.getCbxProveedores().setSelectedIndex(0);
        view.getDpFechaRecepcion().setDate(LocalDate.now());
        view.getTxfCodigo().setText("");
        view.getCbxCategoriaArticulos().setSelectedIndex(0);
        view.getSpnCantidad().setValue(0);
        view.getSpnValorUnitario().setValue(0);
        view.getDpFechaVencimiento().clear();
    }
    
    private void limpiarArticulo() {
        articuloActual = null;
        view.getTxfCodigo().setText("");
        view.getCbxCategoriaArticulos().setSelectedIndex(0);
        view.getSpnCantidad().setValue(0);
        view.getSpnValorUnitario().setValue(0);
        view.getDpFechaVencimiento().clear();
    }
    
    private int getIntFromTex(String number) {
        int result = -1;
        try {
            result = Integer.valueOf(number);
        } catch(NumberFormatException e) {
            mostrarInformacion("Ingrese un número.");
        }
        return result;
    }

    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
