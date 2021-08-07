package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.EstadoVentaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;

public class EVService extends AbstractService<EstadoVenta>{
    
    private static EVService instance;
    
    public EVService() {
        super(new EstadoVentaDao());
    }
    
    public static EVService getInstance() {
        if (instance == null)
            instance = new EVService();
        return instance;
    }
    
}