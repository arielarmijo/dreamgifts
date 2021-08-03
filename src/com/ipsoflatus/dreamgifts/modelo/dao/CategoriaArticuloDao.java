package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import javax.persistence.EntityManager;

public class CategoriaArticuloDao extends AbstractSoftDeleteDao<CategoriaArticulo> {
    
    public CategoriaArticuloDao() {
        super(CategoriaArticulo.class);
    }

    @Override
    protected void update(EntityManager em, CategoriaArticulo ca) {
        CategoriaArticulo categoriaArticulo = em.find(CategoriaArticulo.class, ca.getId());
        categoriaArticulo.setCodigo(ca.getCodigo());
        categoriaArticulo.setNombre(ca.getNombre());
        categoriaArticulo.setEstado(ca.getEstado());
    }

}