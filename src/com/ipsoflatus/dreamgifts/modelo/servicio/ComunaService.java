package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ComunaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;

public class ComunaService extends AbstractService<Comuna> {
    
    private static ComunaService instance;
    
    private ComunaService() {
        super(new ComunaDao());
    }
    
    public static ComunaService getInstance() {
        if (instance == null)
            instance = new ComunaService();
        return instance;
    }

}
