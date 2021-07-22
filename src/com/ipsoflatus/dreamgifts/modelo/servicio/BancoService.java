package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.BancoDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;

public class BancoService extends AbstractService<Banco> {

    private static BancoService instance;
    
    private BancoService() {
        super(new BancoDao());
    }
    
    public static BancoService getInstance() {
        if (instance == null)
            instance = new BancoService();
        return instance;
    }
    
}
