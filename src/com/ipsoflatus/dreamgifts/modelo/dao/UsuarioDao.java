package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import javax.persistence.EntityManager;

public class UsuarioDao extends AbstractSoftDeleteDao<Usuario>{

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public void delete(Usuario u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = u.getId() == null ? em.find(Usuario.class, u.getId()) : u;
        em.remove(usuario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void update(EntityManager em, Usuario u) {
        Usuario usuario = em.find(Usuario.class, u.getId());
        usuario.setNombre(u.getNombre());
        usuario.setClave(u.getClave());
        usuario.setEstado(u.getEstado());
    }
    
}
