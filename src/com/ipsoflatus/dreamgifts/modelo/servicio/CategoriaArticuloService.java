package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.dao.CategoriaArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.ArrayList;
import java.util.List;

public class CategoriaArticuloService implements Service<CategoriaArticulo>, ObservableService<Observer<CategoriaArticulo>> {

    private final CategoriaArticuloDao dao;
    private final List<Observer<CategoriaArticulo>> obs;
    private static CategoriaArticuloService instance;
    
    private CategoriaArticuloService() {
        this.dao = new CategoriaArticuloDao();
        this.obs = new ArrayList<>();
    }
    
    public static CategoriaArticuloService getInstance() {
        if (instance == null)
            instance = new CategoriaArticuloService();
        return instance;
    }
    
    @Override
    public List<CategoriaArticulo> buscar() {
        return dao.findAll();
    }
    
    @Override
    public CategoriaArticulo buscar(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<CategoriaArticulo> buscar(String text) {
        return dao.findByTermLike(text);
    }

    @Override
    public void guardar(CategoriaArticulo ca) {
        dao.save(ca);
        notifyObservers();
    }

    @Override
    public void editar(CategoriaArticulo ca) {
        dao.update(ca);
        notifyObservers();
    }
    
    @Override
    public void cambiarEstado(List<Integer> ids, Boolean estado) {
        dao.updateStateByIds(ids, estado);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<CategoriaArticulo> o) {
        this.obs.add(o);
        o.actualizar(dao.findAll());
    }

    @Override
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizar(dao.findAll());
        });
    }
    
}
