package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ComunaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import java.util.ArrayList;
import java.util.List;
import com.ipsoflatus.dreamgifts.modelo.Observer;
import java.util.stream.Collectors;

public class ComunaService implements Service<Comuna>, ObservableService<Observer> {
    
    private final ComunaDao comunaDao;
    private final List<Observer> obs;
    private static ComunaService instance;
    
    private ComunaService() {
        comunaDao = new ComunaDao();
        obs = new ArrayList<>();
    }
    
    public static ComunaService getInstance() {
        if (instance == null)
            instance = new ComunaService();
        return instance;
    }

    @Override
    public List<Comuna> buscar() {
        return comunaDao.findAll();
    }
    
    @Override
    public Comuna buscar(String codigo) {
        return comunaDao.findByCode(codigo);
    }
    
    @Override
    public void guardar(Comuna comuna) {
        comunaDao.save(comuna);
        notifyObservers();
    }
    
    @Override
    public void editar(Integer id, Comuna c) {
        Comuna comuna = comunaDao.findById(id);
        comuna.setCodigo(c.getCodigo());
        comuna.setNombre(c.getNombre());
        comuna.setEstado(c.getEstado());
        comunaDao.update(comuna);
        notifyObservers();
    }
    
    @Override
    public void addObserver(Observer o) {
        obs.add(o);
    }
    
    @Override
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizar(comunaDao.findAll());
        });
    }

}
