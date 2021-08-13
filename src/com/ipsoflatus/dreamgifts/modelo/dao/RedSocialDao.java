package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import javax.persistence.EntityManager;

public class RedSocialDao extends AbstractSoftDeleteDao<RedSocial> {

    public RedSocialDao() {
        super(RedSocial.class);
    }
    
    public void updateEntity(RedSocial rs) {
        RedSocial redSocial = findById(rs.getId());
        redSocial.setCodigo(rs.getCodigo());
        redSocial.setNombre(rs.getNombre());
        redSocial.setEstado(rs.getEstado());
    }
    
}
