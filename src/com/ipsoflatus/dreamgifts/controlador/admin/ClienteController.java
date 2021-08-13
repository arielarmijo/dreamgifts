package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ClienteTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.ClienteView;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteController {

    private final ClienteService clienteSrv = ClienteService.getInstance();
    private final ClienteView view;
    private final ClienteTableModel tableModel;
    private Cliente clienteActual;

    public ClienteController(ClienteView view) {
        this.view = view;
        this.tableModel = (ClienteTableModel) view.getjTable().getModel();
    }

    public void cancelar() {
        clienteActual = null;
        view.getjTextFieldRut().setText("");
        view.getjTextFieldNombre().setText("");
        view.getjTextFieldApellido().setText("");
        view.getjTextFieldCelular().setText("");
        view.getjTextFieldDireccion().setText("");
        view.getjTextFieldEmail().setText("");
        view.getjTextFieldTelefono().setText("");
        view.getjComboBoxComuna().setSelectedIndex(0);
        view.getDatePicker().clear();
    }

    public void grabar() {
        String rut = view.getjTextFieldRut().getText();
        String nombre = view.getjTextFieldNombre().getText();
        String apellido = view.getjTextFieldApellido().getText();
        String correo = view.getjTextFieldEmail().getText();
        String direccion = view.getjTextFieldDireccion().getText();
        Comuna comuna = (Comuna) view.getjComboBoxComuna().getSelectedItem();

        String telefono = view.getjTextFieldTelefono().getText();
        String celular = view.getjTextFieldCelular().getText();

        if (rut.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || direccion.isEmpty() || celular.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }

        LocalDate fecha = view.getDatePicker().getDate();
        if (fecha == null) {
            view.mostrarInformacion("Ingrese Fecha de Nacimiento.");
            return;
        }
        Date fechaNacimiento = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());

        try {
            if (clienteActual == null) {
                Cliente cliente = new Cliente();
                cliente.setRut(rut);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setCorreo(correo);
                cliente.setDireccion(direccion);
                cliente.setComuna(comuna);
                cliente.setTelefono(telefono);
                cliente.setCelular(celular);
                cliente.setFechaNacimiento(fechaNacimiento);
                cliente.setEstado(Boolean.TRUE);
                clienteSrv.guardar(cliente);

            } else {
                clienteActual.setRut(rut);
                clienteActual.setNombre(nombre);
                clienteActual.setApellido(apellido);
                clienteActual.setCorreo(correo);
                clienteActual.setDireccion(direccion);
                clienteActual.setComuna(comuna);
                clienteActual.setCelular(celular);
                clienteActual.setTelefono(telefono);
                clienteActual.setFechaNacimiento(fechaNacimiento);
                clienteSrv.editar(clienteActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }

    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<Cliente> cliente = termino.isEmpty() ? clienteSrv.buscar() : clienteSrv.buscar(termino);
        tableModel.actualizar(cliente);
        view.getjTextFieldBuscar().setText("");
    }

    public void editar() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione cliente.");
            return;
        }
        clienteActual = tableModel.getItem(row);
        view.getjTextFieldRut().setText(clienteActual.getRut());
        view.getjTextFieldNombre().setText(clienteActual.getNombre());
        view.getjTextFieldApellido().setText(clienteActual.getApellido());
        view.getjTextFieldCelular().setText(clienteActual.getCelular());
        view.getjTextFieldDireccion().setText(clienteActual.getDireccion());
        view.getjTextFieldEmail().setText(clienteActual.getCorreo());
        view.getjTextFieldTelefono().setText(clienteActual.getTelefono());
        view.getjComboBoxComuna().getModel().setSelectedItem(clienteActual.getComuna());
        Date date = clienteActual.getFechaNacimiento();
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        view.getDatePicker().setDate(localDate);
    }
    
    public void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }
    
    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }
    
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Seleccione Cliente");
            return;
        }
        try {
            clienteSrv.cambiarEstado(ids, estado);
            tableModel.selectAll(false);  
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
    }

    public void vender() {
        int row = view.getjTable().getSelectedRow();
        if (row != -1) {
            Cliente c = tableModel.getItem(row);
            if (c.getEstado()) {
                 VentaView vv = ((VentaView) view.getRoot().venta);
                vv.getTxfRut().setText(c.getRut());
                vv.getTxfNombreCliente().setText(c.getNombre() + " " + c.getApellido());
                vv.getTxfTelefonoCliente().setText(c.getTelefono());
                vv.getTxfEmailCliente().setText(c.getCorreo());
            }
        }
        view.getRoot().showVentasTab(0);   
    }

}
