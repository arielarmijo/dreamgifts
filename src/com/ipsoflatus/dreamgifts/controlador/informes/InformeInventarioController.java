package com.ipsoflatus.dreamgifts.controlador.informes;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.servicio.ExportService;
import com.ipsoflatus.dreamgifts.modelo.servicio.FacturaDetalleService;
import com.ipsoflatus.dreamgifts.modelo.tabla.informes.InventarioTableModel;
import com.ipsoflatus.dreamgifts.vista.informes.InformeInventarioView;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InformeInventarioController implements DateChangeListener {
    
    private final ExportService exportSrv;
    private final FacturaDetalleService fdSrv;
    private final InformeInventarioView view;
    private final InventarioTableModel tableModel;
    private List<FacturaDetalle> items;

    public InformeInventarioController(InformeInventarioView view) {
        this.exportSrv = new ExportService();
        this.fdSrv = FacturaDetalleService.getInstance();
        this.view = view;
        this.tableModel = (InventarioTableModel) view.getjTable().getModel();
        this.items = fdSrv.buscar();
    }

    public void filtrarPorCategoriaArticulo() {
        CategoriaArticulo categoria = (CategoriaArticulo) view.getCbxCategoriasArticulo().getSelectedItem();
        Proveedor proveedor = (Proveedor) view.getCbxProveedores().getSelectedItem();
        List<FacturaDetalle> tmp = filtrarPorCategoriaArticulo(items, categoria);
        tableModel.setItems(filtrarPorProveedor(tmp, proveedor));
    }
    
    public void filtrarPorProveedor() {
        Proveedor proveedor = (Proveedor) view.getCbxProveedores().getSelectedItem();
        CategoriaArticulo categoria = (CategoriaArticulo) view.getCbxCategoriasArticulo().getSelectedItem();
        List<FacturaDetalle> tmp = filtrarPorProveedor(items, proveedor);
        tableModel.setItems(filtrarPorCategoriaArticulo(tmp, categoria));
    }
    
    @Override
    public void dateChanged(DateChangeEvent dce) {
        
        LocalDate desde = view.getDpDesde().getDate();
        LocalDate hasta = view.getDpHasta().getDate();
        
        if (desde != null && hasta != null) {
            Date desdeDate = Date.from(desde.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date hastaDate = Date.from(hasta.atStartOfDay(ZoneId.systemDefault()).toInstant());
            items = fdSrv.filtrarPorFecha(desdeDate, hastaDate);
        } else if (desde != null && hasta == null) {
            Date desdeDate = Date.from(desde.atStartOfDay(ZoneId.systemDefault()).toInstant());
            items = fdSrv.filtrarPorFechaDesde(desdeDate);
        } else if (desde == null && hasta != null) {
            Date hastaDate = Date.from(hasta.atStartOfDay(ZoneId.systemDefault()).toInstant());
            items = fdSrv.filtrarPorFechaHasta(hastaDate);
        } else {
            items = fdSrv.buscar();
        }

        List<FacturaDetalle> tmp;
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriasArticulo().getSelectedItem();
        tmp = filtrarPorCategoriaArticulo(items, ca);
        
        Proveedor p = (Proveedor) view.getCbxProveedores().getSelectedItem();
        tmp = filtrarPorProveedor(tmp, p);
        
        tableModel.setItems(tmp);
        
    }
    
    public void exportarAExcel() {
        exportSrv.exportarAExcel(view.getjTable(), "Inventario");
    }
    
    private List<FacturaDetalle> filtrarPorCategoriaArticulo(List<FacturaDetalle> ffdd, CategoriaArticulo ca) {
        List<FacturaDetalle> items = ffdd;
        if (ca != null && ca.getId() != null)
            items = items.stream().filter(fd -> fd.getArticulo().getCategoriaArticulo().equals(ca)).collect(Collectors.toList());
        return items;
    }
    
    private List<FacturaDetalle> filtrarPorProveedor(List<FacturaDetalle> ffdd,Proveedor p) {
        List<FacturaDetalle> items = ffdd;
        if (p.getId() != null) 
            items = items.stream().filter(fd -> fd.getFactura().getOrdenCompra().getProveedor().equals(p)).collect(Collectors.toList());
        return items;
    }

}
