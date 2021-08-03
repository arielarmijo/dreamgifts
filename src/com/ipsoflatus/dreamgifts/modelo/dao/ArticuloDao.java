package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import javax.persistence.EntityManager;

public class ArticuloDao extends AbstractSoftDeleteDao<Articulo> {

    public ArticuloDao() {
        super(Articulo.class);
    }

    @Override
    protected void update(EntityManager em, Articulo a) {
        Articulo articulo = em.find(Articulo.class, a.getId());
        articulo.setNombre(a.getNombre());
        articulo.setMarca(a.getMarca());
        articulo.setStock(a.getStock());
        articulo.setFechaVencimiento(a.getFechaVencimiento());
        articulo.setEstado(a.getEstado());
        articulo.setCategoriaArticulo(a.getCategoriaArticulo());
    }
    
}
