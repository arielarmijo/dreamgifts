package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static jdk.nashorn.internal.codegen.ObjectClassGenerator.pack;

public class OrdenCompraDao extends AbstractDao<OrdenCompra> {

    public OrdenCompraDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), OrdenCompra.class);
    }

    @Override
    public void update(OrdenCompra oc) throws Exception {
        
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();  
            
            OrdenCompra persistentOC = em.find(OrdenCompra.class, oc.getId());
            Query query = em.createQuery("DELETE FROM orden_compra_detalle ocd WHERE ocd.ordenCompra.id = :id");
            query.setParameter("id", persistentOC.getId());
            query.executeUpdate();
            
            List<OrdenCompraDetalle> articulos = oc.getArticulos();
            for (OrdenCompraDetalle articulo : articulos) {
                articulo.setOrdenCompra(persistentOC);
                em.persist(articulo);
            }
            
            persistentOC.setArticulos(articulos);
            persistentOC.setFechaOrden(oc.getFechaOrden());
            persistentOC.setProveedor(oc.getProveedor());
            em.merge(persistentOC);

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

}
