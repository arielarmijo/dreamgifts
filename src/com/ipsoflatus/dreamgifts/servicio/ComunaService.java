package com.ipsoflatus.dreamgifts.servicio;

import com.ipsoflatus.dreamgifts.dao.ComunaDao;
import com.ipsoflatus.dreamgifts.modelo.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import java.util.ArrayList;
import java.util.List;

public class ComunaService {
    
    private final ComunaDao comunaDao;
    private List<ComunaObserver> obs;
    private static final ComunaService instance = new ComunaService();
    
    private ComunaService() {
        comunaDao = new ComunaDao();
        obs = new ArrayList<>();
    }
    
    public static ComunaService getInstance() {
        return instance;
    }
    
    public void addObserver(ComunaObserver o) {
        System.out.println("Agregando observador...");
        obs.add(o);
        System.out.println("Observadores: " + obs.size());
    }
    
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizarComunas(comunaDao.findAll());
            System.out.println(o);
        });
    }

    public List<Comuna> buscarComunas() {
        return comunaDao.findAll();
    }
    
    public void guardarComuna(Comuna comuna) {
        comunaDao.save(comuna);
        notifyObservers();
    }
    
}
