package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ComunaDao implements DAO<Comuna> {
    
    private final EntityManagerFactory emf;
    
    public ComunaDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public ComunaDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<Comuna> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Comuna> comunas;
        Query query = em.createQuery("SELECT c FROM Comuna c");
        comunas = query.getResultList();
        em.close();
        return comunas;
    }

    @Override
    public List<Comuna> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Comuna c WHERE UPPER(c.codigo) LIKE UPPER(:term) OR UPPER(c.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<Comuna> comunas = query.getResultList();
        em.close();
        return comunas;
    }

    @Override
    public Comuna findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Comuna c WHERE c.id = :id");
        query.setParameter("id", id);
        Comuna comuna = (Comuna) query.getSingleResult();
        em.close();
        return comuna;
    }

    @Override
    public void save(Comuna c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Comuna c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Comuna comuna = em.find(Comuna.class, c.getId());
        comuna.setCodigo(c.getCodigo());
        comuna.setNombre(c.getNombre());
        comuna.setEstado(c.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT c FROM Comuna c WHERE c.id IN :ids");
        query.setParameter("ids", ids);
        List<Comuna> comunas = query.getResultList();
        comunas.forEach(c -> {
            c.setEstado((estado));
        });
        em.getTransaction().commit();
        em.close();
    }

}
