package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Usuario;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsuarioDao extends AbstractSoftDeleteDao<Usuario>{

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public Usuario findByName(String nombre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            String namedQuery = "Usuario.findByName";
            TypedQuery<Usuario> query = em.createNamedQuery(namedQuery, Usuario.class);
            query.setParameter("name", nombre);
            Usuario result = query.getSingleResult();
            return result;
        } catch (Exception e) {
            printError(typeClass, e);
            throw new DreamGiftsException("No se encontró usuario.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void delete(Usuario u) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();  
            Usuario usuario = em.merge(u);
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            printError(typeClass, e);
            throw new DreamGiftsException("Error al borrar usuario.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void updateEntity(Usuario u) {
        Usuario usuario = findById(u.getId());
        usuario.setNombre(u.getNombre());
        usuario.setClave(u.getClave());
        usuario.setEstado(u.getEstado());
    }
    
}
