package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.table.ClienteTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.ClienteView;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteController implements Controller<ClienteView>{
    
    private final ClienteService clienteSrv = ClienteService.getInstance();
    private ClienteView view;
    private ClienteTableModel tableModel;
    private Cliente clienteActual;
    private final ComunaService comunaService = ComunaService.getInstance();

    public ClienteController() {

    }

    @Override
    public void setView(ClienteView view) {
        this.view = view;
        this.tableModel = (ClienteTableModel) view.getjTable2().getModel();
    }

    @Override
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

    @Override
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
            mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        LocalDate fecha = view.getDatePicker().getDate();
       
       if (fecha == null){
           mostrarInformacion("Ingrese Fecha de Nacimiento.");
           return;
       }
            Date fechaNac = Date.valueOf(fecha);
        try {
            if (clienteActual == null) {
               clienteSrv.guardar(new Cliente(rut, nombre, apellido, correo, direccion, comuna.getId(), fechaNac, telefono, celular, true));
            
            }else{
                clienteActual.setRut(rut);
                clienteActual.setNombre(nombre);
                clienteActual.setApellido(apellido);
                clienteActual.setCorreo(correo);
                clienteActual.setDireccion(direccion);
                clienteActual.setComunaId(comuna.getId());
                clienteActual.setCelular(celular);
                clienteActual.setTelefono(telefono);
                clienteActual.setFechaNacimiento(fechaNac);
                clienteSrv.editar(clienteActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<Cliente> cliente = termino.isEmpty() ? clienteSrv.buscar(): clienteSrv.buscar(termino);
        tableModel.actualizar(cliente);
        view.getjTextFieldBuscar().setText("");
    }

    @Override
    public void editar() {
       int row = view.getjTable2().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione cliente.");
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
        Comuna comuna = comunaService.buscar(clienteActual.getComunaId());
        view.getjComboBoxComuna().getModel().setSelectedItem(comuna);
        
       // view.getDatePicker().setDate(LocalDate.of(clienteActual.getFechaNacimiento()));
        
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
  List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione Cliente");
            return;
        }
        clienteSrv.cambiarEstado(ids, estado);
        tableModel.selectAll(false);  
    }
    
    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void vender() {
        int row = view.getjTable2().getSelectedRow();
        if (row != -1) {
            Cliente c = tableModel.getItem(row);
            VentaView vv = ((VentaView) view.getRoot().venta);
            vv.getTxfRut().setText(c.getRut());
            vv.getTxfNombreCliente().setText(c.getNombre());
            vv.getTxfTelefonoCliente().setText(c.getTelefono());
            vv.getTxfEmailCliente().setText(c.getCorreo());
        }
        
        view.getRoot().showVentasTab(0);
    }
    
}
