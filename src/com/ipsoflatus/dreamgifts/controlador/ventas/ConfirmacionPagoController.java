package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.modelo.table.ventas.ConfirmacionPagoTableModel;
import com.ipsoflatus.dreamgifts.vista.ventas.ConfirmacionPagoView;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class ConfirmacionPagoController {

    private final ConfirmacionPagoView view;
    private final EVService evSrv = EVService.getInstance();
    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final VentaService ventaSrv = VentaService.getInstance();
    private Venta ventaActual;
    
    public ConfirmacionPagoController(ConfirmacionPagoView view) {
        this.view = view;
    }
    
    public void cancelar() {
        ventaActual = null;
        view.getTxfVentaId().setText("");
        view.getTxfNombreCliente().setText("");
        view.getTxfRut().setText("");
        view.getDpFechaPago().setDate(LocalDate.now());
        view.getCbxBancos().setSelectedIndex(0);
        view.getTxfCodigoTransaccion().setText("");
    }

    public void confirmarPago() {
        
        if (ventaActual == null) {
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
        
        ventaActual.setBancoId(bancoId);
        ventaActual.setFechaTransferencia(fechaPago);
        ventaActual.setCodigoTransferencia(codigo);
        
        
        try {
            List<EstadoVenta> eevv = evSrv.buscar();
            eevv.forEach(System.out::println);
            if (eevv.size() < 2)
               throw new DreamGiftsException("No hay suficientes estados de ventas creados.");
            EstadoVenta ev = eevv.get(1);
            System.out.println(ev.getNombre());
            ventaActual.setEstadoVentaId(ev.getId());
            ventaSrv.editar(ventaActual);
            mostrarInformacion("Pago registrado.");
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
        
    }

    public void buscarVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void seleccionarVenta() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione venta.");
            return;
        }
        ConfirmacionPagoTableModel tableModel = (ConfirmacionPagoTableModel) this.view.getjTable().getModel();
        ventaActual = tableModel.getItem(row);
        view.getTxfVentaId().setText(ventaActual.getId().toString());
        Cliente c = clienteSrv.buscar(ventaActual.getClienteId());
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
