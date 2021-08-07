package com.ipsoflatus.dreamgifts.controlador.informes;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.ipsoflatus.dreamgifts.modelo.servicio.ExportService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.informes.ClienteTableModel;
import com.ipsoflatus.dreamgifts.vista.informes.InformeClientesView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InformeClientesController implements DateChangeListener {

    private final VentaService ventaSrv;
    private final ExportService exportSrv;
    private final InformeClientesView view;
    private final ClienteTableModel tableModel;
    
    public InformeClientesController(InformeClientesView view) {
        this.ventaSrv = VentaService.getInstance();
        this.exportSrv = new ExportService();
        this.view = view;
        this.tableModel = (ClienteTableModel) view.getjTable().getModel();
    }

    public void buscar() {
        String rut = view.getTxfRut().getText();
        tableModel.actualizar(rut.isEmpty() ? ventaSrv.buscar() : ventaSrv.buscar(rut));
        view.getTxfRut().setText("");
    }

    public void exportarAExcel() {
        exportSrv.exportarAExcel(view.getjTable(), "Clientes");
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
    public void dateChanged(DateChangeEvent dce) {
        view.getTxfRut().setText("");
        dce.getSource().setDate(dce.getNewDate());
        LocalDate desdeLocalDate = view.getDpDesde().getDate();
        Date desdeDate = Date.from(desdeLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate hastaLocalDate = view.getDpHasta().getDate();
        Date hastaDate = Date.from(hastaLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tableModel.actualizar(ventaSrv.buscarPorFecha(desdeDate, hastaDate));
    }

}
