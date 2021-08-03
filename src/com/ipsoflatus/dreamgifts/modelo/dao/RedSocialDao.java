package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import javax.persistence.EntityManager;

public class RedSocialDao extends AbstractSoftDeleteDao<RedSocial> {

    public RedSocialDao() {
        super(RedSocial.class);
    }
    
    @Override
    protected void update(EntityManager em, RedSocial rs) {
        RedSocial redSocial = em.find(RedSocial.class, rs.getId());
        redSocial.setCodigo(rs.getCodigo());
        redSocial.setNombre(rs.getNombre());
        redSocial.setEstado(rs.getEstado());
    }
    
}
