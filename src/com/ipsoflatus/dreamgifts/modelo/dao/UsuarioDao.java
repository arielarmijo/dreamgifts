package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsuarioDao extends AbstractSoftDeleteDao<Usuario>{

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public Usuario findByName(String nombre) {
        EntityManager em = emf.createEntityManager();
        String namedQuery = "Usuario.findByName";
        TypedQuery<Usuario> query = em.createNamedQuery(namedQuery, Usuario.class);
        query.setParameter("name", nombre);
        Usuario result = query.getSingleResult();
        em.close();
        return result;
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
