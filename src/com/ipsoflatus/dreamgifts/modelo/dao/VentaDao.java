package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class VentaDao extends AbstractDao<Venta> {
    
    private final PackService packSrv = PackService.getInstance();

    public VentaDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), Venta.class);
    }
    
    @Override
    public void save(Venta v) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Pack pack = em.merge(v.getPack());
            pack.setStock(pack.getStock() - 1);
            em.persist(v);
            em.getTransaction().commit();
            packSrv.notifyObservers();
        } catch (Exception e) {
            printError(e);
            throw new DreamGiftsException("Error al guardar venta.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Date findMinDate() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Venta.findMinDate");
            TypedQuery<Date> query = em.createNamedQuery(namedQuery, Date.class);
            Date result = query.getSingleResult();
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Date findMaxDate() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            String namedQuery = String.format("Venta.findMaxDate");
            TypedQuery<Date> query = em.createNamedQuery(namedQuery, Date.class);
            Date result = query.getSingleResult();
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
     public List<Venta> findByDateBetween(Date desde, Date hasta) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Venta.findByDateBetween");
            TypedQuery<Venta> query = em.createNamedQuery(namedQuery, Venta.class);
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            List<Venta> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
