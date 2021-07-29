package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.table.ConfirmacionPagoTableModel;
import com.ipsoflatus.dreamgifts.vista.ventas.ConfirmacionPagoView;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class ConfirmacionPagoController {

    private final ConfirmacionPagoView view;
    private final ClienteService clienteSrv = ClienteService.getInstance();
    
    public ConfirmacionPagoController(ConfirmacionPagoView view) {
        this.view = view;
    }
    
    public void cancelar() {
        view.getTxfVentaId().setText("");
        view.getTxfNombreCliente().setText("");
        view.getTxfRut().setText("");
        view.getDpFechaPago().setDate(LocalDate.now());
        view.getCbxBancos().setSelectedIndex(0);
        view.getTxfCodigoTransaccion().setText("");
    }

    public void confirmarPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Venta v = tableModel.getItem(row);
        view.getTxfVentaId().setText(v.getId().toString());
        Cliente c = clienteSrv.buscar(v.getClienteId());
        view.getTxfNombreCliente().setText(c.getNombre() + " " + c.getApellido());
        view.getTxfRut().setText(c.getRut());

    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

}
