package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.VentaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;

public class VentaService extends AbstractService<Venta> {
    
    private static VentaService instance;

    private VentaService() {
        super(new VentaDao());
    }
    
    public static VentaService getInstance() {
        if (instance == null)
            instance = new VentaService();
        return instance;
    } 

}
