package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.Usuario;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioController {
    
    private UsuarioView view;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;
    
    public void setView(UsuarioView view) {
        this.view = view;
        usuarioDao =  new UsuarioDao();
        usuarioActual = null;
    }
    
    public List<Usuario> obtenerListadoUsuarios() {
        return usuarioDao.findAll();
    }

    public void guardarUsuario(String nombre, String password, String rePassword) {
        // Verifica que los campos tengan información
        if (nombre.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verifica que los passwords coincidan
        if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Agrega usuario y actualiza la tabla
        try {
            boolean esGuardar = (usuarioActual == null);
            if (esGuardar) {
                usuarioActual = new Usuario(nombre, password);
                usuarioDao.save(usuarioActual);
            } else {
                usuarioActual.setNombre(nombre);
                usuarioActual.setClave(password);
                usuarioDao.update(usuarioActual);
            }
            String mensaje = String.format("Usuario %s con éxito.", esGuardar ? "guardado" : "actualizado");
            JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            view.actualizarTabla(usuarioDao.findAll());
            view.limpiarCamposRegistro();
            usuarioActual = null;
        } catch (DreamGiftsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelarRegistro() {
        view.limpiarCamposRegistro();
        usuarioActual = null;
    }

    public void buscarUsuario(String nombreBuscado) {
        List<Usuario> usuarios;
        if (!nombreBuscado.isEmpty()) {
            usuarios = usuarioDao.findByNameLike(nombreBuscado);
            view.actualizarTabla(usuarios);
            view.limpiarCampoBuscar();
        } else {
            usuarios = usuarioDao.findAll();
            view.actualizarTabla(usuarios);
        }
    }

    public void buscarUsuarioPorId(int id) {
        usuarioActual = usuarioDao.findById(id);
        view.actualizarCamposRegistro(usuarioActual);
        
    }
    
    public void activarUsuariosSeleccionados(List<Integer> ids, boolean estado) {
        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            usuarioDao.activateUsersByIds(ids, estado);
            view.actualizarTabla(usuarioDao.findAll());
            //String mensaje = String.format("Usuarios %s.", estado ? "activados" : "desactivados");
            //JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
