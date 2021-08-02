package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.UsuarioDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;

public class UsuarioService extends AbstractService<Usuario> {
    
    private static UsuarioService instance;
    
    private UsuarioService() {
        super(new UsuarioDao());
    }
    
    public static UsuarioService getInstance() {
        if (instance == null)
            instance = new UsuarioService();
        return instance;
    }

    public void borrar(Usuario usuario) {
        ((UsuarioDao) dao).delete(usuario);
        notifyObservers();
    }
    
}
