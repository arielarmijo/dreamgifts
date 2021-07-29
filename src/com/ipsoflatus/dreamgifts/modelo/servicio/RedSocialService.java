package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.RedSocialDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;

public class RedSocialService extends AbstractService<RedSocial> {
    
    private static RedSocialService instance;

    private RedSocialService() {
        super(new RedSocialDao());
    }
    
    public static RedSocialService getInstance() {
        if (instance == null)
            instance = new RedSocialService();
        return instance;
    }

}
