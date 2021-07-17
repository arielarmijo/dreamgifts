/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador;

import com.ipsoflatus.dreamgifts.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.entidad.Usuario;
import com.ipsoflatus.dreamgifts.vista.DreamGifts;
import com.ipsoflatus.dreamgifts.vista.Login;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class LoginController {
    
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private Login login;
    
    public void setView(Login login) {
        this.login = login;
    }
    
    public void checkCredentials(String nombre, String clave) {
//        if (nombre.isEmpty() || clave.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Información", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        Usuario usuario = usuarioDao.findByName(nombre);
//        if (usuario == null || !usuario.getClave().equals(clave)) {
//            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña inválida.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (!usuario.isActive()) {
//            JOptionPane.showMessageDialog(null, "Usuario desactivado.", "Información", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        } 
        EventQueue.invokeLater(() -> {
            new DreamGifts().setVisible(true);
            login.dispose();
        });
    }
    
}
