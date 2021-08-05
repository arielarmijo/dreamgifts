package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.FacturaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;

public class FacturaService extends AbstractService<Factura> {

    private static FacturaService instance;

    private FacturaService() {
        super(new FacturaDao());
    }
    
    public static FacturaService getInstance() {
        if (instance == null)
            instance = new FacturaService();
        return instance;
    }
    
}
