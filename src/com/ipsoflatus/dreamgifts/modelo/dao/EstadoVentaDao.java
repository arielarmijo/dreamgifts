package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import javax.persistence.EntityManager;

public class EstadoVentaDao extends AbstractSoftDeleteDao<EstadoVenta> {

    public EstadoVentaDao() {
        super(EstadoVenta.class);
    }

    @Override
    public void update(EstadoVenta ev) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();  
        EstadoVenta estadoVenta = em.find(EstadoVenta.class, ev.getId());
        estadoVenta.setCodigo(ev.getCodigo());
        estadoVenta.setNombre(ev.getNombre());
        estadoVenta.setEstado(ev.getEstado());
        em.getTransaction().commit();
        em.close();
    }

}
