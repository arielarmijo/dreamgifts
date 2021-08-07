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
        Usuario usuario = em.merge(u);
        em.remove(usuario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Usuario u) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();  
        Usuario usuario = em.find(Usuario.class, u.getId());
        usuario.setNombre(u.getNombre());
        usuario.setClave(u.getClave());
        usuario.setEstado(u.getEstado());
        em.getTransaction().commit();
        em.close();
    }
    
}
