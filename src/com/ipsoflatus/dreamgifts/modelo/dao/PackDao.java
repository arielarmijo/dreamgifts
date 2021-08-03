package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import javax.persistence.EntityManager;

public class PackDao extends AbstractSoftDeleteDao<Pack> {

    public PackDao() {
        super(Pack.class);
    }

    @Override
    protected void update(EntityManager em, Pack p) {
        Pack pack = em.find(Pack.class, p.getId());
        pack.setNombre(p.getNombre());
        pack.setStock(p.getStock());
        pack.setCosto(p.getCosto());
        pack.setEstado(p.getEstado());
        pack.setArticulos(p.getArticulos());
    }

}
