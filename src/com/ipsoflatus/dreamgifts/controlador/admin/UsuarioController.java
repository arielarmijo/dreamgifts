package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.Usuario;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import java.util.List;

public class UsuarioController {
    
    private UsuarioView view;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;
    
    public UsuarioController() {
        usuarioDao =  new UsuarioDao();
        usuarioActual = null;
    }
    
    public void setView(UsuarioView view) {
        this.view = view;
    }
    
    public List<Usuario> obtenerListadoUsuarios() {
        return usuarioDao.findAll();
    }
    
    public void cancelar() {
        reset();
    }
    
    public void grabar(String nombre, String password, String rePassword) {
        if (nombre.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        if (!password.equals(rePassword)) {
            view.mostrarInformacion("Los passwords no coinciden.");
            return;
        }
        if (usuarioActual == null) {
            guardar(nombre, password);
        } else {
            actualizar(nombre, password);
        }
    }
    
    public void buscar(String nombreBuscado) {
        List<Usuario> usuarios;
        if (nombreBuscado.isEmpty()) {
            usuarios = usuarioDao.findAll();
        } else {
            usuarios = usuarioDao.findByNameLike(nombreBuscado);
            view.mostrarEstado("Resultados de búsqueda para " + nombreBuscado);
            view.limpiarCampoBuscar();
        }
        view.actualizarTabla(usuarios);
    }
    
    public void editar(String nombre) {
        usuarioActual = usuarioDao.findByName(nombre);
        view.actualizarCamposRegistro(usuarioActual);
        view.mostrarEstado("Editando usuario " + nombre);
    }

    public void activarUsuariosSeleccionados(List<String> nombres, boolean estado) {
        if (nombres.isEmpty()) {
            view.mostrarError("Seleccione usuarios.");
        } else {
            usuarioDao.activateUsersByNames(nombres, estado);
            view.actualizarTabla(usuarioDao.findAll());
            view.mostrarEstado("Usuarios modificados.");
        }
    }
    
    private void guardar(String nombre, String password) {
        try {
            usuarioActual = new Usuario(nombre, password);
            usuarioDao.save(usuarioActual);
            view.mostrarEstado("Usuario guardado con éxito.");
            view.actualizarTabla(usuarioDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }
    
    private void actualizar(String nombre, String password) {
        try {
            usuarioActual.setNombre(nombre);
            usuarioActual.setClave(password);
            usuarioDao.update(usuarioActual);
            view.mostrarEstado("Usuario actualizado con éxito.");
            view.actualizarTabla(usuarioDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }
    
    private void reset() {
        view.limpiarCamposRegistro();
        usuarioActual = null;
    }
    
}
