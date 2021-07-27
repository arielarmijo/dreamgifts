package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.PackDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;

public class PackService extends AbstractService<Pack> {
    
    private static PackService instance;

    private PackService() {
        super(new PackDao());
    }
    
    public static PackService getInstance() {
        if (instance == null)
            instance = new PackService();
        return instance;
    }

}
