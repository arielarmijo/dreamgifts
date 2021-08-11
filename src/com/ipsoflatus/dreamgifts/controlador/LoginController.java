package com.ipsoflatus.dreamgifts.controlador;

import com.ipsoflatus.dreamgifts.modelo.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import com.ipsoflatus.dreamgifts.vista.DreamGifts;
import com.ipsoflatus.dreamgifts.vista.Login;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {
    
    private final UsuarioDao usuarioDao;
    private Login login;
    
    public LoginController() {
        usuarioDao =  new UsuarioDao();
    }
    
    public void setView(Login login) {
        this.login = login;
    }
    
    public void checkCredentials(String nombre, String clave) {
        
        if (nombre.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Usuario usuario = usuarioDao.findByName(nombre);
        if (usuario == null || !usuario.getClave().equals(clave)) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!usuario.getEstado()) {
            JOptionPane.showMessageDialog(null, "Usuario desactivado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        EventQueue.invokeLater(() -> {
            JFrame app =new DreamGifts();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
            login.dispose();
        });
        
    }
    
}
