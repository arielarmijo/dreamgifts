package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import javax.persistence.EntityManager;

public class ComunaDao extends AbstractSoftDeleteDao<Comuna> {
    
    public ComunaDao() {
        super(Comuna.class);
    }

    @Override
    protected void update(EntityManager em, Comuna c) {
        Comuna comuna = em.find(Comuna.class, c.getId());
        comuna.setCodigo(c.getCodigo());
        comuna.setNombre(c.getNombre());
        comuna.setEstado(c.getEstado());
    }

}
