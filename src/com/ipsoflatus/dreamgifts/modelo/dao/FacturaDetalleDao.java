package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class FacturaDetalleDao extends AbstractDao<FacturaDetalle>  {

    public FacturaDetalleDao() {
        super(FacturaDetalle.class);
    }

    public void updateEntity(FacturaDetalle t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FacturaDetalle> findByDateBetween(Date desde, Date hasta) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("FacturaDetalle.findByDateBetween");
            TypedQuery<FacturaDetalle> query = em.createNamedQuery(namedQuery, FacturaDetalle.class);
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            List<FacturaDetalle> result = query.getResultList();
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
    
    public List<FacturaDetalle> findByDateSmallerThan(Date hasta) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("FacturaDetalle.findByDateSmallerThan");
            TypedQuery<FacturaDetalle> query = em.createNamedQuery(namedQuery, FacturaDetalle.class);
            query.setParameter("hasta", hasta);
            List<FacturaDetalle> result = query.getResultList();
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
    
    public List<FacturaDetalle> findByDateGreaterThan(Date desde) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("FacturaDetalle.findByDateGreaterThan");
            TypedQuery<FacturaDetalle> query = em.createNamedQuery(namedQuery, FacturaDetalle.class);
            query.setParameter("desde", desde);
            List<FacturaDetalle> result = query.getResultList();
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
