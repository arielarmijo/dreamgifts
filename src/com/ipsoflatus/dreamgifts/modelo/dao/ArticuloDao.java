package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import java.util.List;
import javax.persistence.EntityManager;

public class ArticuloDao extends AbstractSoftDeleteDao<Articulo> {

    private final CategoriaArticuloService caSrv = CategoriaArticuloService.getInstance();
    
    public ArticuloDao() {
        super(Articulo.class);
    }

    @Override
    public void save(Articulo t) {
        super.save(t);
        caSrv.notifyObservers();
    }
    
    public void updateEntity(Articulo a) {
        Articulo articulo = findById(a.getId());
        articulo.setNombre(a.getNombre());
        articulo.setMarca(a.getMarca());
        articulo.setStock(a.getStock());
        articulo.setFechaVencimiento(a.getFechaVencimiento());
        articulo.setEstado(a.getEstado());
        articulo.setCategoriaArticulo(a.getCategoriaArticulo());
        caSrv.notifyObservers();
    }
    
    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        super.updateStateByIds(ids, estado);
        caSrv.notifyObservers();
    }

}
