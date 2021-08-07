package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.OrdenCompraDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompra;

public class OrdenCompraService extends AbstractService<OrdenCompra> {

    private static OrdenCompraService instance;

    private OrdenCompraService() {
        super(new OrdenCompraDao());
    }
    
    public static OrdenCompraService getInstance() {
        if (instance == null)
            instance = new OrdenCompraService();
        return instance;
    }
    
}
