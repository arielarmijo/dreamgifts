package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.Usuario;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UsuarioController {
    
    private UsuarioView view;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;
    private final JLabel estado;
    
    public UsuarioController(JLabel label) {
        this.estado = label;
    }
    
    public void setView(UsuarioView view) {
        this.view = view;
        usuarioDao =  new UsuarioDao();
        usuarioActual = null;
    }
    
    public List<Usuario> obtenerListadoUsuarios() {
        //return usuarioDao.findAll();
        return new ArrayList<Usuario>() {{
            add(new Usuario(1, "test", "1234", false));
        }};
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
//        try {
//            boolean esGuardar = (usuarioActual == null);
//            if (esGuardar) {
//                usuarioActual = new Usuario(nombre, password);
//                usuarioDao.save(usuarioActual);
//            } else {
//                usuarioActual.setNombre(nombre);
//                usuarioActual.setClave(password);
//                usuarioDao.update(usuarioActual);
//            }
//            String mensaje = String.format("Usuario %s con éxito.", esGuardar ? "guardado" : "actualizado");
//            estado.setText(mensaje);
//            JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
//            view.actualizarTabla(usuarioDao.findAll());
//            view.limpiarCamposRegistro();
//            usuarioActual = null;
//        } catch (DreamGiftsException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void cancelarRegistro() {
        view.limpiarCamposRegistro();
        usuarioActual = null;
    }

    public void buscarUsuario(String nombreBuscado) {
//        List<Usuario> usuarios;
//        if (!nombreBuscado.isEmpty()) {
//            usuarios = usuarioDao.findByNameLike(nombreBuscado);
//            view.actualizarTabla(usuarios);
//            view.limpiarCampoBuscar();
//            estado.setText(String.format("Resultado de búsqueda para %s", nombreBuscado));
//        } else {
//            usuarios = usuarioDao.findAll();
//            view.actualizarTabla(usuarios);
//            estado.setText(String.format("Usuarios registrados %d", usuarios.size()));
//        }
    }

    public void buscarUsuarioPorId(int id) {
//        usuarioActual = usuarioDao.findById(id);
//        view.actualizarCamposRegistro(usuarioActual);
//        estado.setText(String.format("Editando datos de usuario %s", usuarioActual.getNombre()));
    }
    
    public void activarUsuariosSeleccionados(List<Integer> ids, boolean estado) {
//        if (ids.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Seleccione usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            usuarioDao.activateUsersByIds(ids, estado);
//            view.actualizarTabla(usuarioDao.findAll());
//            this.estado.setText(String.format("Usuario%1$s %2$s%1$s", ids.size() > 1 ? "s" : "", estado ? "activado" : "desactivado"));
//        }
    }
    
}
