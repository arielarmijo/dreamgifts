package com.ipsoflatus.dreamgifts.controlador.informes;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.ipsoflatus.dreamgifts.modelo.servicio.ExportService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.informes.VentaTableModel;
import com.ipsoflatus.dreamgifts.vista.informes.InformeVentasView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class InformeVentaController implements TableModelListener, DateChangeListener {
    
    private final ExportService exportSrv;
    private final VentaService ventaSrv;
    private final InformeVentasView view;
    private final VentaTableModel tableModel;

    public InformeVentaController(InformeVentasView view) {
        this.exportSrv = new ExportService();
        this.ventaSrv = VentaService.getInstance();
        this.view = view;
        this.tableModel = (VentaTableModel) view.getjTable().getModel();
    }

    public void buscar() {
        String rut = view.getTxfRut().getText();
        tableModel.actualizar(rut.isEmpty() ? ventaSrv.buscar() : ventaSrv.buscar(rut));
        view.getTxfRut().setText("");
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

    @Override
    public void tableChanged(TableModelEvent e) {
        view.getDpDesde().getSettings().setDateRangeLimits(obtenerFechaMinima(), obtenerFechaMaxima());
        view.getDpHasta().getSettings().setDateRangeLimits(obtenerFechaMinima(), obtenerFechaMaxima());
    }
    
    @Override
    public void dateChanged(DateChangeEvent dce) {
        view.getTxfRut().setText("");
        //dce.getSource().getSettings().setDateRangeLimits(obtenerFechaMinima(), obtenerFechaMinima());
        dce.getSource().setDate(dce.getNewDate());
        LocalDate desdeLocalDate = view.getDpDesde().getDate();
        Date desdeDate = Date.from(desdeLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate hastaLocalDate = view.getDpHasta().getDate();
        Date hastaDate = Date.from(hastaLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tableModel.actualizar(ventaSrv.filtrarPorFecha(desdeDate, hastaDate));
    }

    public void exportarAExcel() {
        exportSrv.exportarAExcel(view.getjTable(), "Ventas");
    }

}
