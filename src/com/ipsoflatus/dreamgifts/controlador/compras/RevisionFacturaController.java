package com.ipsoflatus.dreamgifts.controlador.compras;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ProveedorService;
import com.ipsoflatus.dreamgifts.modelo.tabla.compras.DetalleFacturaTableModel;
import com.ipsoflatus.dreamgifts.modelo.tabla.compras.FacturaTableModel;
import com.ipsoflatus.dreamgifts.vista.compras.RegistroCompraView;
import com.ipsoflatus.dreamgifts.vista.compras.RevisionFacturaView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class RevisionFacturaController implements TableModelListener, DateChangeListener  {

    private final FacturaService facturaSrv;
    private final RevisionFacturaView view;
    private final FacturaTableModel facturaTableModel;
    private final DetalleFacturaTableModel facturaDetalleTableModel;
    private List<Factura> items;
    
    public RevisionFacturaController(RevisionFacturaView view) {
        this.facturaSrv = FacturaService.getInstance();
        this.view = view;
        this.facturaTableModel = (FacturaTableModel) view.getjTableFacturas().getModel();
        this.facturaDetalleTableModel = (DetalleFacturaTableModel) view.getjTableDetallesFactura().getModel();
        this.items = facturaTableModel.getItems();
    }

    public void filtrarPorProveedor() {
        view.getTxfNumeroFactura().setText("");
        Proveedor proveedor = (Proveedor) view.getCbxProveedores().getSelectedItem();
        System.out.println(proveedor.getId() + " " + proveedor.getRazonSocial());
        List<Proveedor> proveedores = proveedor.getId() == null ? ProveedorService.getInstance().buscar() : Arrays.asList(proveedor);
        List<Factura> tmp;
        if (proveedor.getId() == null)
            tmp = items;
        else
            tmp = items.stream().filter(f -> f.getOrdenCompra().getProveedor().equals(proveedor)).collect(Collectors.toList());
        facturaTableModel.setItems(tmp);
    }
    
    public LocalDate obtenerFechaMinima() {
        Date date = facturaSrv.obtenerFechaMinima();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        //return LocalDate.now();
    }
    
    public LocalDate obtenerFechaMaxima() {
        Date date = facturaSrv.obtenerFechaMaxima();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
      //return LocalDate.now();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
//        view.getDpDesde().setDate(obtenerFechaMinima());
//        view.getDpHasta().setDate(obtenerFechaMaxima());
        view.getDpDesde().getSettings().setDateRangeLimits(obtenerFechaMinima(), obtenerFechaMaxima());
        view.getDpHasta().getSettings().setDateRangeLimits(obtenerFechaMinima(), obtenerFechaMaxima());
    }

    @Override
    public void dateChanged(DateChangeEvent dce) {
        
        view.getTxfNumeroFactura().setText("");
        
        dce.getSource().setDate(dce.getNewDate());
        
        LocalDate desdeLocalDate = view.getDpDesde().getDate();
        Date desdeDate = Date.from(desdeLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        LocalDate hastaLocalDate = view.getDpHasta().getDate();
        Date hastaDate = Date.from(hastaLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        items = facturaSrv.buscarPorFecha(desdeDate, hastaDate);
        facturaTableModel.actualizar(items);
        
        
    }

    public void buscarNumeroFactura() {
        
        String numeroFacturaTexto = view.getTxfNumeroFactura().getText();
        if (numeroFacturaTexto.isEmpty()) {
            return;
        }
        
        try {
            Integer numeroFactura = Integer.valueOf(numeroFacturaTexto);
            Proveedor p = (Proveedor) view.getCbxProveedores().getSelectedItem();
            List<Factura> tmp;
            if (p.getId() == null) {
                tmp = items.stream().filter(f -> f.getNumero() == numeroFactura).collect(Collectors.toList());
            } else {
                tmp = items.stream().filter(f -> f.getNumero() == numeroFactura && f.getOrdenCompra().getProveedor().equals(p)).collect(Collectors.toList());
            }
            
            facturaTableModel.setItems(tmp);
            view.getTxfNumeroFactura().setText("");
        } catch(NumberFormatException e) {
            e.printStackTrace();
            mostrarInformacion("Ingrese un número.");
            view.getTxfNumeroFactura().setText("");
        } catch(Exception e) {
            //e.printStackTrace();
            mostrarError(e.getMessage());
        }
    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarDetalle() {
        int row = view.getjTableFacturas().getSelectedRow();
        Factura factura = facturaTableModel.getItem(row);
        facturaDetalleTableModel.setItems(factura.getArticulos());
    }

    public void editarFactura() {
      int row = view.getjTableFacturas().getSelectedRow();
        if (row > -1) {
            Factura factura = facturaTableModel.getItem(row);
            RegistroCompraView rc = ((RegistroCompraView) view.getRoot().registroCompras);
            rc.getTxfNumeroFactura().setText(String.valueOf(factura.getNumero()));
        }
        view.getRoot().showComprasTab(2);   
    }

}
