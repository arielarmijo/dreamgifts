package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T> implements DAO<T> {

    protected final EntityManagerFactory emf;
    protected final Class<T> typeClass;
    
    public AbstractDao(Class<T> typeClass) {
        this(Persistence.createEntityManagerFactory("dreamgifts"), typeClass);
    }

    public AbstractDao(EntityManagerFactory emf, Class<T> typeClass) {
        this.emf = emf;
        this.typeClass = typeClass;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String sql = String.format("SELECT o FROM %s o", typeClass.getSimpleName());
            Query query = em.createQuery(sql);
            results = query.getResultList();
        } catch (Exception e) {
            printError(e);
            //throw new DreamGiftsException("No se encontraron registros.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return results;
    }

    @Override
    public List<T> findByTermLike(String term) {
        List<T> result = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String namedQuery = String.format("%s.findByTermLike", typeClass.getSimpleName());
            TypedQuery<T> query = em.createNamedQuery(namedQuery, typeClass);
            query.setParameter("term", "%" + term + "%");
            result = query.getResultList();
        } catch (Exception e) {
            printError(e);
            //throw new DreamGiftsException("No se encontron registros.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }
   
    @Override
    public T findById(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            T t = em.find(typeClass, id);
            return t;
        } catch (Exception e) {
            printError(e);
            throw new DreamGiftsException("No se encontró registro.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void save(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();  
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            printError(e);
            throw new DreamGiftsException("Registro duplicado.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();  
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            printError(e);
            throw new DreamGiftsException("Error al actualizar " + typeClass.getSimpleName());
        } finally {
            em.close();
        }
    }
    
    public void printError(Exception e) {
        printError(typeClass, e);
    }
    
}
