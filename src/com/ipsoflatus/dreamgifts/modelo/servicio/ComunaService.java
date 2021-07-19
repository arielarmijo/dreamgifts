package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ComunaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import java.util.ArrayList;
import java.util.List;
import com.ipsoflatus.dreamgifts.modelo.Observer;

public class ComunaService implements Service<Comuna>, ObservableService<Observer<Comuna>> {
    
    private final ComunaDao dao;
    private final List<Observer<Comuna>> obs;
    private static ComunaService instance;
    
    private ComunaService() {
        dao = new ComunaDao();
        obs = new ArrayList<>();
    }
    
    public static ComunaService getInstance() {
        if (instance == null)
            instance = new ComunaService();
        return instance;
    }

    @Override
    public List<Comuna> buscar() {
        return dao.findAll();
    }
    
    @Override
    public Comuna buscar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Comuna> buscar(String text) {
        return dao.findByTermLike(text);
    }
    
    @Override
    public void guardar(Comuna comuna) {
        dao.save(comuna);
        notifyObservers();
    }
    
    @Override
    public void editar(Comuna c) {
        dao.update(c);
        notifyObservers();
    }
    
    @Override
    public void addObserver(Observer<Comuna> o) {
        obs.add(o);
        o.actualizar(dao.findAll());
    }
    
    @Override
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizar(dao.findAll());
        });
    }

    @Override
    public void cambiarEstado(List<Integer> ids, Boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
