package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UsuarioDao extends AbstractDao<Usuario>{

    public UsuarioDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), Usuario.class);
        searchSql = "SELECT u FROM Usuario u WHERE UPPER(u.nombre) LIKE UPPER(:term)";
    }
    
    public void delete(Usuario u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, u.getId());
        em.remove(usuario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void setEstado(Usuario usuario, boolean estado) {
        usuario.setEstado(estado);
    }

    @Override
    protected void update(EntityManager em, Usuario u) {
        Usuario usuario = em.find(Usuario.class, u.getId());
        usuario.setNombre(u.getNombre());
        usuario.setClave(u.getClave());
        usuario.setEstado(u.getEstado());
    }
    
}
