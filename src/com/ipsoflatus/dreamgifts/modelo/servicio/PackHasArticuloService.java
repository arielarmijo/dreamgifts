package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.PackHasArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;

public class PackHasArticuloService extends AbstractService<PackHasArticulo> {

    private static PackHasArticuloService instance;
    
    private PackHasArticuloService() {
        super(new PackHasArticuloDao());
    }
    
    public static PackHasArticuloService getInstance() {
        if (instance == null)
            instance = new PackHasArticuloService();
        return instance;
    }

}
