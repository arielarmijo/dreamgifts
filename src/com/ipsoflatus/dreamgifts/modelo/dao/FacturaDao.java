package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FacturaDao extends AbstractDao<Factura> {

    public FacturaDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), Factura.class);
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
            ex.printStackTrace();
            throw new DreamGiftsException(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
