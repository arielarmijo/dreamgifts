package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.entidad.Usuario;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UsuarioController implements TableModelListener {
    
    private final UsuarioDao usuarioDao;
    private final List<String> usuariosSeleccionados;
    private List<Usuario> usuarios;
    private UsuarioView view;
    private Usuario usuarioActual;
    
    public UsuarioController() {
        usuarioDao =  new UsuarioDao();
        usuariosSeleccionados = new ArrayList<>();
        usuarios = usuarioDao.findAll();
        usuarioActual = null;
    }
    
    public void setView(UsuarioView view) {
        this.view = view;
    }
    
    public void actualizarTabla() {
        if (usuarios.isEmpty()) {
            view.mostrarEstado("No se encontraron registros.");
        } else {
            view.actualizarTabla(usuarios);
            usuarios.forEach(System.out::println);
        }
    }
    
    public void cancelar() {
        usuarioActual = null;
        view.setNombre("");
        view.setNuevoPassword("");
        view.setRePassword("");
        view.setActivo(true);
        view.mostrarEstado("Administración: Usuarios.");
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
                Usuario usuario = usuarioDao.save(new Usuario(nombre, password, estado));
                usuarios.add(usuario);
                mensaje = "Usuario guardado con éxito.";
            } else {
                usuarioActual.setNombre(nombre);
                usuarioActual.setClave(password);
                usuarioActual.setEstado(estado);
                Usuario usuario = usuarioDao.update(usuarioActual);
                usuarios.set(usuarios.indexOf(usuarioActual), usuario);
                mensaje = "Usuario actualizado con éxito.";
            }
            actualizarTabla();
            view.setNombre("");
            view.setNuevoPassword("");
            view.setRePassword("");
            view.mostrarEstado(mensaje);
            usuarioActual = null;
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }    
   
    public void buscar(String nombre) {
        usuarios = nombre.isEmpty() ? usuarioDao.findAll() : usuarioDao.findByNameLike(nombre);
        actualizarTabla();
        view.mostrarEstado(String.format("Mostrando %d registros.", usuarios.size()));
    }
    
    public void editar(String nombre) {
        usuarioActual = usuarioDao.findByName(nombre);
        view.setNombre(usuarioActual.getNombre());
        view.setNuevoPassword(usuarioActual.getClave());
        view.setRePassword(usuarioActual.getClave());
        view.setEstado(usuarioActual.getEstado());
        view.mostrarEstado("Editando usuario " + usuarioActual.getNombre());
    }
    
    public void borrar(String nombre) {
        Usuario usuario = usuarioDao.findByName(nombre);
        usuarioDao.delete(usuario.getId());
        usuarios.remove(usuario);
        actualizarTabla();
        view.mostrarEstado("Usuario borrado.");
        if (usuarioActual != null) {
            usuarioActual = null;
            view.setNombre("");
            view.setNuevoPassword("");
            view.setRePassword("");
            view.setActivo(true);
        }
    }
    
    public void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }

    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }

    public void activarDesactivarSeleccionados(boolean estado) {
        if (usuariosSeleccionados.isEmpty()) {
            view.mostrarError("Seleccione usuarios.");
        } else {
            List<Usuario> users = usuarioDao.activateByNames(usuariosSeleccionados, estado);
            users.forEach(u -> {
                usuarios.set(usuarios.indexOf(u), u);
            });
            actualizarTabla();
            usuariosSeleccionados.clear();
            view.setSeleccionarTodos(false);
            view.mostrarEstado(String.format("Usuarios %s", estado ? "activados" : "desactivados"));
        }
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (row >= 0 && column >= 0) {
            TableModel model = (TableModel) e.getSource();
            boolean seleccionado = (boolean) model.getValueAt(row, column);
            String codigo = (String) model.getValueAt(row, 0);
            if (seleccionado) {
                usuariosSeleccionados.add(codigo);
            } else {
                usuariosSeleccionados.remove(codigo);
            }
            System.out.println("Usuarios seleccionados: " + usuariosSeleccionados);
        }
    }
    
}
