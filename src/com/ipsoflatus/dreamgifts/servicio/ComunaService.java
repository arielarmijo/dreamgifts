package com.ipsoflatus.dreamgifts.servicio;

import com.ipsoflatus.dreamgifts.dao.ComunaDao;
import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import java.util.ArrayList;
import java.util.List;

public class ComunaService {
    
    private final ComunaDao comunaDao;
    private final List<ComunaObserver> obs;
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
    
    public void addObserver(ComunaObserver o) {
        obs.add(o);
    }
    
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizarComunas(comunaDao.findAll());
        });
    }

    public List<Comuna> buscarComunas() {
        return comunaDao.findAll();
    }
    
    public Comuna buscarComuna(String codigo) {
        return comunaDao.findByCode(codigo);
    }
    
    public void guardarComuna(Comuna comuna) {
        comunaDao.save(comuna);
        notifyObservers();
    }
    
    public void editarComuna(Integer id, Comuna c) {
        Comuna comuna = comunaDao.findById(id);
        comuna.setCodigo(c.getCodigo());
        comuna.setNombre(c.getNombre());
        comuna.setEstado(c.getEstado());
        comunaDao.update(comuna);
        notifyObservers();
    }

}
