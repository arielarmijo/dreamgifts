package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import javax.persistence.EntityManager;

public class BancoDao extends AbstractSoftDeleteDao<Banco> {
    
    public BancoDao() {
        super(Banco.class);
    }

    @Override
    protected void update(EntityManager em, Banco b) {
        Banco banco = em.find(Banco.class, b.getId());
        banco.setCodigo(b.getCodigo());
        banco.setNombre(b.getNombre());
        banco.setEstado(b.getEstado());
        em.getTransaction().commit();
    }

}
