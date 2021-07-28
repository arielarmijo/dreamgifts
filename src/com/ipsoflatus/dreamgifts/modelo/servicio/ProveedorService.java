package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ProveedorDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;

public class ProveedorService extends AbstractService<Proveedor> {

    private static ProveedorService instance;
    
    private ProveedorService() {
        super(new ProveedorDao());
    }
    
    public static ProveedorService getInstance() {
        if (instance == null)
            instance = new ProveedorService();
        return instance;
    }

}
