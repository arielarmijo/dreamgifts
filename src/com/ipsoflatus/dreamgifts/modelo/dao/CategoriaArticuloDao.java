package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CategoriaArticuloDao implements DAO<CategoriaArticulo> {

    private final EntityManagerFactory emf;
    
    public CategoriaArticuloDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public CategoriaArticuloDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<CategoriaArticulo> findAll() {
        EntityManager em = emf.createEntityManager();
        List<CategoriaArticulo> ccaa;
        Query query = em.createQuery("SELECT ca FROM CategoriaArticulo ca");
        ccaa = query.getResultList();
        em.close();
        return ccaa;
    }

    @Override
    public List<CategoriaArticulo> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ca FROM CategoriaArticulo ca WHERE UPPER(ca.codigo) LIKE UPPER(:term) OR UPPER(ca.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<CategoriaArticulo> ccaa = query.getResultList();
        em.close();
        return ccaa;
    }

    @Override
    public CategoriaArticulo findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ca FROM CategoriaArticulo ca WHERE ca.id = :id");
        query.setParameter("id", id);
        CategoriaArticulo ca = (CategoriaArticulo) query.getSingleResult();
        em.close();
        return ca;
    }
    
    @Override
    public void save(CategoriaArticulo ca) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(ca);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(CategoriaArticulo ca) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        CategoriaArticulo categoriaArticulo = em.find(CategoriaArticulo.class, ca.getId());
        categoriaArticulo.setCodigo(ca.getCodigo());
        categoriaArticulo.setNombre(ca.getNombre());
        categoriaArticulo.setEstado(ca.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT ca FROM CategoriaArticulo ca WHERE ca.id IN :ids");
        query.setParameter("ids", ids);
        List<CategoriaArticulo> ccaa = query.getResultList();
        ccaa.forEach(ca -> {
            ca.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }

}