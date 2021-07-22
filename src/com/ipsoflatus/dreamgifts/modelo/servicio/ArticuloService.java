package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;

public class ArticuloService extends AbstractService<Articulo> {

    private static ArticuloService instance;
    
    private ArticuloService() {
        super(new ArticuloDao());
    }

    public static ArticuloService getInstance() {
        if (instance == null)
            instance = new ArticuloService();
        return instance;
    }
    
}
