package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PackDao implements DAO<Pack> {

    private final EntityManagerFactory emf;

    public PackDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public PackDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<Pack> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Pack> packs;
        Query query = em.createQuery("SELECT p FROM Pack p");
        packs = query.getResultList();
        em.close();
        return packs;
    }

    @Override
    public List<Pack> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Pack p WHERE UPPER(p.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<Pack> packs = query.getResultList();
        em.close();
        return packs;
    }

    @Override
    public Pack findById(int id) {
        EntityManager em = emf.createEntityManager();
        Pack pack = em.find(Pack.class, id);
        em.close();
        return pack;
    }

    @Override
    public void save(Pack p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Pack p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Pack pack = em.find(Pack.class, p.getId());
        pack.setNombre(p.getNombre());
        pack.setStock(p.getStock());
        pack.setCosto(p.getCosto());
        pack.setEstado(p.getEstado());
        pack.setArticulos(p.getArticulos());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT p FROM Pack p WHERE p.id IN :ids");
        query.setParameter("ids", ids);
        List<Pack> packs = query.getResultList();
        packs.forEach(p -> {
            p.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }

}
