package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ProveedorService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ProveedorTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.ProveedorView;
import com.ipsoflatus.dreamgifts.vista.compras.SolicitudPedidoView;
import java.util.List;
import java.util.stream.Collectors;

public class ProveedorController implements Controller<ProveedorView> {

    private Proveedor proveedorActual;
    private ProveedorView view;
    private ProveedorTableModel tableModel;
    private final ComunaService comunaService = ComunaService.getInstance();
    private final ProveedorService proveedorService = ProveedorService.getInstance();
    
    @Override
    public void setView(ProveedorView view) {
        this.view = view;
        this.tableModel = (ProveedorTableModel) view.getjTableProveedores().getModel();
    }

    @Override
    public void cancelar() {
        proveedorActual = null;
        view.getjTextFieldRut().setText("");
        view.getjTextFieldContacto().setText("");
        view.getjTextFieldDireccion().setText("");
        view.getjTextFieldRazonSocial().setText("");
        view.getjTextFieldEmail().setText("");
        view.getjTextFieldTelefono().setText("");
        view.getjComboBoxComunas().setSelectedIndex(0);
    }

    @Override
    public void grabar() {
        
        String rut = view.getjTextFieldRut().getText();
        String razonSocial = view.getjTextFieldRazonSocial().getText();
        String contacto = view.getjTextFieldContacto().getText();
        String direccion = view.getjTextFieldDireccion().getText();
        String telefono = view.getjTextFieldTelefono().getText();
        String  email = view.getjTextFieldEmail().getText();
        
        if (rut.isEmpty() || razonSocial.isEmpty() || contacto.isEmpty() ||
            direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        Comuna comuna = (Comuna) view.getjComboBoxComunas().getSelectedItem();
        if (comuna.getId() == null) {
            mostrarInformacion("Seleccione comuna.");
            return;
        }
        
        try {
            if (proveedorActual == null) {
                Proveedor p = new Proveedor();
                p.setRut(rut);
                p.setRazonSocial(razonSocial);
                p.setContacto(contacto);
                p.setDireccion(direccion);
                p.setComuna(comuna);
                p.setTelefono(telefono);
                p.setEmail(email);
                p.setEstado(Boolean.TRUE);
                proveedorService.guardar(p);
            } else {
                proveedorActual.setRut(rut);
                proveedorActual.setRazonSocial(razonSocial);
                proveedorActual.setContacto(contacto);
                proveedorActual.setDireccion(direccion);
                proveedorActual.setComuna(comuna);
                proveedorActual.setTelefono(telefono);
                proveedorActual.setEmail(email);
                proveedorService.editar(proveedorActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<Proveedor> proveedores = termino.isEmpty() ? proveedorService.buscar(): proveedorService.buscar(termino);
        tableModel.actualizar(proveedores);
    }

    @Override
    public void editar() {
        int row = view.getjTableProveedores().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione proveedor.");
            return;
        }
        proveedorActual = tableModel.getItem(row);
        System.out.println(proveedorActual);
        view.getjTextFieldRut().setText(proveedorActual.getRut());
        view.getjTextFieldRazonSocial().setText(proveedorActual.getRazonSocial());
        view.getjTextFieldContacto().setText(proveedorActual.getContacto());
        view.getjTextFieldDireccion().setText(proveedorActual.getDireccion());
        view.getjTextFieldTelefono().setText(proveedorActual.getTelefono());
        view.getjTextFieldEmail().setText(proveedorActual.getEmail());
        view.getjComboBoxComunas().getModel().setSelectedItem(proveedorActual.getComuna());
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione proveedor(es).");
            return;
        }
        proveedorService.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void comprar() {
        int row = view.getjTableProveedores().getSelectedRow();
        if (row != -1) {
            Proveedor p = tableModel.getItem(row);
            SolicitudPedidoView sp = ((SolicitudPedidoView) view.getRoot().solicitudPedido);
            sp.getCbxProveedores().setSelectedItem(p);
        }
        view.getRoot().showComprasTab(1);  
    }

   
}
