package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import javax.persistence.EntityManager;

public class BancoDao extends AbstractSoftDeleteDao<Banco> {
    
    public BancoDao() {
        super(Banco.class);
    }

    @Override
    public void update(Banco b) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();  
        Banco banco = em.find(Banco.class, b.getId());
        banco.setCodigo(b.getCodigo());
        banco.setNombre(b.getNombre());
        banco.setEstado(b.getEstado());
        em.getTransaction().commit();
        em.close();
    }

}
