package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.CategoriaArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;

public class CategoriaArticuloService extends AbstractService<CategoriaArticulo> {

    private static CategoriaArticuloService instance;
    
    private CategoriaArticuloService() {
       super(new CategoriaArticuloDao());
    }
    
    public static CategoriaArticuloService getInstance() {
        if (instance == null)
            instance = new CategoriaArticuloService();
        return instance;
    }
    
}
