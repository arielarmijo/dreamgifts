package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import javax.persistence.EntityManager;

public class BancoDao extends AbstractSoftDeleteDao<Banco> {
    
    public BancoDao() {
        super(Banco.class);
    }

    public void updateEntity(Banco b) {
        Banco banco = findById(b.getId());
        banco.setCodigo(b.getCodigo());
        banco.setNombre(b.getNombre());
        banco.setEstado(b.getEstado());
    }

}
