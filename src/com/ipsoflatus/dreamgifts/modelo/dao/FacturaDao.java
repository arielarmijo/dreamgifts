package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FacturaDao extends AbstractDao<Factura> {

    public FacturaDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), Factura.class);
    }
    
    @Override
    public void save(Factura f) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            List<FacturaDetalle> ffdd = f.getArticulos();
            for (FacturaDetalle fd : ffdd) {
                Articulo a = em.merge(fd.getArticulo());
                a.setStock(a.getStock() + fd.getCantidad());
            }
            em.persist(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void update(Factura f) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();  
            
            //Factura persistentFactura = em.find(Factura.class, f.getId());
//            Query query = em.createQuery("DELETE FROM OrdenCompraDetalle ocd WHERE ocd.ordenCompra.id = :id");
//            query.setParameter("id", persistentOC.getId());
//            query.executeUpdate();
//            
//            List<OrdenCompraDetalle> articulos = oc.getArticulos();
//            for (OrdenCompraDetalle articulo : articulos) {
//                articulo.setOrdenCompra(persistentOC);
//                em.persist(articulo);
//            }
//            
//            persistentOC.setArticulos(articulos);
//            persistentOC.setFechaOrden(oc.getFechaOrden());
//            persistentOC.setProveedor(oc.getProveedor());
            //em.merge(persistentFactura);

            em.getTransaction().commit();
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Factura findByNumber(Integer numeroFactura) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Factura.findByNumber");
            TypedQuery<Factura> query = em.createNamedQuery(namedQuery, Factura.class);
            query.setParameter("numero", numeroFactura);
            Factura result = query.getSingleResult();
            em.getTransaction().commit();
            return result;
        } catch (NoResultException ex) {
            //ex.printStackTrace();
            em.getTransaction().rollback();
            throw new DreamGiftsException("Factura N° " + numeroFactura + " no existe.");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findByProveedor(List<Proveedor> proveedores) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Factura.findByProveedorIn");
            TypedQuery<Factura> query = em.createNamedQuery(namedQuery, Factura.class);
            query.setParameter("proveedor", proveedores);
            List<Factura> result = query.getResultList();
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

    public Date findMinDate() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Factura.findMinDate");
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
            String namedQuery = String.format("Factura.findMaxDate");
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
    
    public List<Factura> findByDateBetween(Date desde, Date hasta) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin(); 
            String namedQuery = String.format("Factura.findByDateBetween");
            TypedQuery<Factura> query = em.createNamedQuery(namedQuery, Factura.class);
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            List<Factura> result = query.getResultList();
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
