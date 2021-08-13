package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OrdenCompraDao extends AbstractDao<OrdenCompra> {

    public OrdenCompraDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), OrdenCompra.class);
    }

    public void updateEntity(OrdenCompra oc) {
            
//            OrdenCompra persistentOC = em.find(OrdenCompra.class, oc.getId());
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
//            em.merge(persistentOC);
        
    }

}
