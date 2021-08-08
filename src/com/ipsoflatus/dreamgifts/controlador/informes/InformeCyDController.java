package com.ipsoflatus.dreamgifts.controlador.informes;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ExportService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.informes.CyDTableModel;
import com.ipsoflatus.dreamgifts.vista.informes.InformeCyDView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InformeCyDController implements DateChangeListener {
    
    private final ExportService exportSrv;
    private final VentaService ventaSrv;
    private final InformeCyDView view;
    private final CyDTableModel tableModel;
    private List<Venta> ventas;
    
    public InformeCyDController(InformeCyDView view) {
        this.exportSrv = new ExportService();
        this.ventaSrv = VentaService.getInstance();
        this.view = view;
        this.tableModel = (CyDTableModel) view.getjTable().getModel();
        this.ventas = ventaSrv.buscar();
    }

    public LocalDate obtenerFechaMinima() {
        Date date = ventaSrv.obtenerFechaMinima();
        if (date == null)
            return LocalDate.now();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate obtenerFechaMaxima() {
        Date date = ventaSrv.obtenerFechaMaxima();
        if (date == null)
            return LocalDate.now();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void buscar() {
        String rut = view.getTxfRut().getText();
        List<Venta> tmp = filtrarPorRut(ventas, rut);
        tableModel.actualizar(tmp);
        view.getTxfRut().setText("");
    }

    public void exportarAExcel() {
        exportSrv.exportarAExcel(view.getjTable(), "Cambios y Devoluciones");
    }
    
    @Override
    public void dateChanged(DateChangeEvent dce) {
        LocalDate desde = view.getDpDesde().getDate();
        LocalDate hasta = view.getDpHasta().getDate();
        
        if (desde != null && hasta != null) {
            Date desdeDate = Date.from(desde.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date hastaDate = Date.from(hasta.atStartOfDay(ZoneId.systemDefault()).toInstant());
            ventas = ventaSrv.filtrarPorFecha(desdeDate, hastaDate);
        } else if (desde != null && hasta == null) {
            Date desdeDate = Date.from(desde.atStartOfDay(ZoneId.systemDefault()).toInstant());
            ventas = ventaSrv.filtrarPorFecha(desdeDate, ventaSrv.obtenerFechaMaxima());
        } else if (desde == null && hasta != null) {
            Date hastaDate = Date.from(hasta.atStartOfDay(ZoneId.systemDefault()).toInstant());
            ventas = ventaSrv.filtrarPorFecha(ventaSrv.obtenerFechaMinima(), hastaDate);
        } else {
            ventas = ventaSrv.buscar();
        }

        view.getTxfRut().setText("");
        
        tableModel.actualizar(ventas);
        
    }
    
    private List<Venta> filtrarPorRut(List<Venta> ventas, String rut) {
        if (rut.isEmpty())
            return ventas;
        return ventas.stream().filter(v -> v.getCliente().getRut().contains(rut)).collect(Collectors.toList());
    }

}
