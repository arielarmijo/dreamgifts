package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.VentaService;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class VentaController {
    
    private final ClienteService clienteSrv;
    private final VentaService ventaSrv;
    private final VentaView view;
    private Cliente cliente;
    
    public VentaController(VentaView view) {
        this.view = view;
        this.clienteSrv = ClienteService.getInstance();
        this.ventaSrv = VentaService.getInstance();
    }

    public void limpiar() {
        cliente = null;
        view.getTxfNombreCliente().setText("");
        view.getTxfTelefonoCliente().setText("");
        view.getTxfEmailCliente().setText("");
        view.getTxfRut().setText("");
    }
    
    public void cancelar() {
        cliente = null;
        view.getTxfNombreDestinatario().setText("");
        view.getTxfTelefonoDestinatario().setText("");
        view.getTxfDireccionDestinatario().setText("");
        view.getTxaSaludo().setText("");
        view.getCbxComunas().setSelectedIndex(0);
        view.getCbxPacks().setSelectedIndex(0);
        view.getDpFechaEntrega().setDate(LocalDate.now());
        view.getTpHoraInicio().setTime(LocalTime.of(8,0,0));
        view.getTpHoraTermino().setTime(LocalTime.of(20,0,0));
    }

    public void grabar() {
        
        if (cliente == null) {
            mostrarInformacion("La venta debe estar asociada a un cliente.");
            return;
        }
        Integer clienteId = cliente.getId();
        
        String nombreDestinatario = view.getTxfNombreDestinatario().getText().split("\\ ")[0];
        String apellidoDestinatario = view.getTxfNombreDestinatario().getText().split("\\ ")[1];
        String telefonoDestinatario = view.getTxfTelefonoDestinatario().getText();
        String direccionDestinatario = view.getTxfDireccionDestinatario().getText();
        if (nombreDestinatario.isEmpty() || apellidoDestinatario.isEmpty() ||
            telefonoDestinatario.isEmpty() || direccionDestinatario.isEmpty()) {
            mostrarInformacion("Complete los datos del destinatario.");
            return;
        }
        
        String saludo = view.getTxaSaludo().getText();
        
        Comuna comuna = (Comuna) view.getCbxComunas().getSelectedItem();
        Integer comunaId = comuna.getId();
        if (comunaId == null) {
            mostrarInformacion("Selecciones comuna.");
            return;
        }
        
        Pack pack = (Pack) view.getCbxPacks().getSelectedItem();
        Integer packId = pack.getId();
        if (packId == null) {
            mostrarInformacion("Seleccione pack.");
            return;
        }
        Integer total = obtenerTotal();
        
        Date fechaVenta = Date.valueOf(LocalDate.now());
        Date fechaEntrega = Date.valueOf(view.getDpFechaEntrega().getDate());
        Time horaEntregaInicial = Time.valueOf(view.getTpHoraInicio().getTime());
        Time horaEntregaFinal = Time.valueOf(view.getTpHoraTermino().getTime());
        
        Venta venta = new Venta();
        venta.setClienteId(clienteId);
        venta.setTotal(total);
        venta.setNombreDestinatario(nombreDestinatario);
        venta.setApellidoDestinatario(apellidoDestinatario);
        venta.setTelefonoDestinatario(telefonoDestinatario);
        venta.setDireccionDestinatario(direccionDestinatario);
        venta.setComunaId(comunaId);
        venta.setFechaVenta(fechaVenta);
        venta.setFechaEntrega(fechaEntrega);
        venta.setHoraEntregaInicial(horaEntregaInicial);
        venta.setHoraEntregaFinal(horaEntregaFinal);
        venta.setSaludo(saludo);
        
        ventaSrv.guardar(venta);
        
    }

    public void buscar() {
        String rut = view.getTxfRut().getText();
        if (rut.isEmpty()) {
            mostrarInformacion("Ingrese rut del cliente.");
            return;
        }
        cliente = clienteSrv.buscarPorRut(rut);
        if (cliente == null) {
            mostrarInformacion("Cliente no existe.");
            return;
        }
        view.getTxfNombreCliente().setText(cliente.getNombre() + " " + cliente.getApellido());
        view.getTxfEmailCliente().setText(cliente.getCorreo());
        view.getTxfTelefonoCliente().setText(cliente.getTelefono());
    }

    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void actualizarTotal() {
        view.getLblTotal().setText("$" + obtenerTotal());
    }
    
    private Integer obtenerTotal() {
        Pack pack = (Pack) view.getCbxPacks().getSelectedItem();
        return pack.getCosto() == null ? 0 : pack.getCosto();
    }

}
