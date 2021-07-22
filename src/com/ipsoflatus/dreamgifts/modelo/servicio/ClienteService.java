package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.ClienteDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;

public class ClienteService extends AbstractService<Cliente> {

    private static ClienteService instance;
    
    public ClienteService() {
        super(new ClienteDao());
    }
    
    public static ClienteService getInstance() {
        if (instance == null)
            instance = new ClienteService();
        return instance;
    }

}
