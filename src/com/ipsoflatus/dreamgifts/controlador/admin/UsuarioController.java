package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import com.ipsoflatus.dreamgifts.modelo.servicio.UsuarioService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.UsuarioTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class UsuarioController {
    
    private final UsuarioService usuarioSrv;
    private final UsuarioView view;
    private final UsuarioTableModel tableModel;
    private Usuario usuarioActual;
    
    public UsuarioController(UsuarioView view) {
        this.view = view;
        this.tableModel = (UsuarioTableModel) view.getjTableUsuarios().getModel();
        this.usuarioSrv =  UsuarioService.getInstance();
        this.usuarioActual = null;
    }
    
    public void cancelar() {
        usuarioActual = null;
        view.setNombre("");
        view.setNuevoPassword("");
        view.setRePassword("");
        view.setActivo(true);
    }
    
    public void grabar(String nombre, String password, String rePassword, boolean estado) {
        
        if (nombre.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        if (!password.equals(rePassword)) {
            view.mostrarInformacion("Los passwords no coinciden.");
            return;
        }
        
        try {
            String mensaje;
            if (usuarioActual == null) {
                usuarioSrv.guardar(new Usuario(null, nombre, password, estado));
            } else {
                usuarioActual.setNombre(nombre);
                usuarioActual.setClave(password);
                usuarioActual.setEstado(estado);
                usuarioSrv.editar(usuarioActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }    
   
    public void buscar(String nombre) {
        List<Usuario> usuarios = nombre.isEmpty() ? usuarioSrv.buscar(): usuarioSrv.buscar(nombre);
        tableModel.actualizar(usuarios);
    }
    
    public void editar() {
        usuarioActual = tableModel.getItem(view.getSelectedRow());
        view.setNombre(usuarioActual.getNombre());
        view.setNuevoPassword(usuarioActual.getClave());
        view.setRePassword(usuarioActual.getClave());
        view.setEstado(usuarioActual.getEstado());
    }
    
    public void borrar() {
        usuarioActual = tableModel.getItem(view.getSelectedRow());
        String mensaje = String.format("¿Está seguro que quiere borrar al usuario %s?", usuarioActual.getNombre());
        int response = JOptionPane.showConfirmDialog(null, mensaje, "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == 0) {    
            usuarioSrv.borrar(usuarioActual);
            cancelar();
        }
    }
    
    public void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }

    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }

    public void activarDesactivarSeleccionados(boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(u -> u.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Selecciones usuarios");
            return;
        }
        usuarioSrv.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
        view.getjToggleButtonSeleccion().setSelected(false);
        view.getjToggleButtonSeleccion().setText("Seleccionar todos");
    }
    
    public void seleccionarTodos() {
        boolean select = view.getjToggleButtonSeleccion().isSelected();
        tableModel.selectAll(select);
        String text = select ? "Deseleccionar todos" : "Seleccionar todos";
        view.getjToggleButtonSeleccion().setText(text);
    }
    
}
