package com.ipsoflatus.dreamgifts.modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T> implements Dao<T> {

    protected final EntityManagerFactory emf;
    protected final Class<T> typeClass;
    
    public AbstractDao(Class<T> typeClass) {
        this(Persistence.createEntityManagerFactory("dreamgifts"), typeClass);
    }

    public AbstractDao(EntityManagerFactory emf, Class<T> typeClass) {
        this.emf = emf;
        this.typeClass = typeClass;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> results;
        String sql = String.format("SELECT o FROM %s o", typeClass.getSimpleName());
        Query query = em.createQuery(sql);
        results = query.getResultList();
        em.close();
        return results;
    }

    @Override
    public List<T> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        String namedQuery = String.format("%s.findByTermLike", typeClass.getSimpleName());
        TypedQuery<T> query = em.createNamedQuery(namedQuery, typeClass);
        query.setParameter("term", "%" + term + "%");
        List<T> result = query.getResultList();
        em.close();
        return result;
    }
   
    @Override
    public T findById(int id) {
        EntityManager em = emf.createEntityManager();
        T t = em.find(typeClass, id);
        em.close();
        return t;
    }

    @Override
    public void save(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        update(em, t);
        em.getTransaction().commit();
        em.close();
    }

    protected abstract void update(EntityManager em, T t);
    
}
