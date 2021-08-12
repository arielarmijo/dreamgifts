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
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void update(Venta v) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();  
        Venta venta = em.find(Venta.class, v.getId());
        venta.setCliente(v.getCliente());
        venta.setTotal(v.getTotal());
        venta.setFechaVenta(v.getFechaVenta());
        venta.setFechaTransferencia(v.getFechaTransferencia());
        venta.setCodigoTransferencia(v.getCodigoTransferencia());
        venta.setBanco(v.getBanco());
        venta.setNombreDestinatario(v.getNombreDestinatario());
        venta.setApellidoDestinatario(v.getApellidoDestinatario());
        venta.setDireccionDestinatario(v.getDireccionDestinatario());
        venta.setComuna(v.getComuna());
        venta.setTelefonoDestinatario(v.getTelefonoDestinatario());
        venta.setFechaEntrega(v.getFechaEntrega());
        venta.setHoraEntregaInicial(v.getHoraEntregaInicial());
        venta.setHoraEntregaFinal(v.getHoraEntregaFinal());
        venta.setSaludo(v.getSaludo());
        venta.setRedSocial(v.getRedSocial());
        venta.setEstadoVenta(v.getEstadoVenta());
        venta.setPack(v.getPack());
        em.getTransaction().commit();
        em.close();
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
