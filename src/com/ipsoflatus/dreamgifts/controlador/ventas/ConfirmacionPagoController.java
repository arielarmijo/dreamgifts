package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.tabla.ventas.ConfirmacionPagoTableModel;
import com.ipsoflatus.dreamgifts.vista.ventas.ConfirmacionPagoView;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class ConfirmacionPagoController {

    private final EVService evSrv = EVService.getInstance();
    private final VentaService ventaSrv = VentaService.getInstance();
    private final ConfirmacionPagoView view;
    private final ConfirmacionPagoTableModel tableModel;
    private Venta venta;
    
    public ConfirmacionPagoController(ConfirmacionPagoView view) {
        this.view = view;
        this.tableModel = (ConfirmacionPagoTableModel) view.getjTable().getModel();
    }
    
    public void cancelar() {
        venta = null;
        view.getTxfVentaId().setText("");
        view.getTxfNombreCliente().setText("");
        view.getTxfRut().setText("");
        view.getDpFechaPago().setDate(LocalDate.now());
        view.getCbxBancos().setSelectedIndex(0);
        view.getTxfCodigoTransaccion().setText("");
    }

    public void confirmarPago() {
        
        if (venta == null) {
           mostrarInformacion("Seleccione venta a actualizar.");
            return; 
        }
        
        LocalDate date = view.getDpFechaPago().getDate();
        Date fechaPago = Date.valueOf(date);
        
        Banco banco = (Banco) view.getCbxBancos().getSelectedItem();
        Integer bancoId = banco.getId();
        if (bancoId == null) {
            mostrarInformacion("Seleccione banco.");
            return;
        }
        
        String codigoTransaccion = view.getTxfCodigoTransaccion().getText();
        if (codigoTransaccion.isEmpty()) {
            mostrarInformacion("Ingrese código de transacción.");
            return;
        }
        
        Integer codigo = null;
        try {
            codigo = Integer.valueOf(codigoTransaccion);
        } catch (Exception e) {
            mostrarInformacion("Ingrese un código numérico.");
            return;
        }
        
        venta.setBanco(banco);
        venta.setFechaTransferencia(fechaPago);
        venta.setCodigoTransferencia(codigo);
        
        
        try {
            List<EstadoVenta> eevv = evSrv.buscar();
            if (eevv.size() < 2)
               throw new DreamGiftsException("No hay suficientes estados de ventas creados.");
            EstadoVenta ev = eevv.get(1);
            venta.setEstadoVenta(ev);
            ventaSrv.editar(venta);
            mostrarInformacion("Pago registrado.");
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
        
    }

    public void buscarVenta() {
        String termino = view.getTxfBuscar().getText();
        List<Venta> items = termino.isEmpty() ? ventaSrv.buscar(): ventaSrv.buscar(termino);
        tableModel.actualizar(items);
        view.getTxfBuscar().setText("");
    }

    public void seleccionarVenta() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione venta.");
            return;
        }
        ConfirmacionPagoTableModel tableModel = (ConfirmacionPagoTableModel) this.view.getjTable().getModel();
        venta = tableModel.getItem(row);
        view.getTxfVentaId().setText(venta.getId().toString());
        Cliente c = venta.getCliente();
        view.getTxfNombreCliente().setText(c.getNombre() + " " + c.getApellido());
        view.getTxfRut().setText(c.getRut());
    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
