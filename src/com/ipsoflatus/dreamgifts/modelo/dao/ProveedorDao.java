package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import javax.persistence.EntityManager;

public class ProveedorDao extends AbstractSoftDeleteDao<Proveedor> {

    public ProveedorDao() {
        super(Proveedor.class);
    }

    @Override
    public void update(Proveedor p) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();  
        Proveedor proveedor = em.find(Proveedor.class, p.getId());
        proveedor.setRut(p.getRut());
        proveedor.setRazonSocial(p.getRazonSocial());
        proveedor.setContacto(p.getContacto());
        proveedor.setDireccion(p.getDireccion());
        proveedor.setComuna(p.getComuna());
        proveedor.setTelefono(p.getTelefono());
        proveedor.setEmail(p.getEmail());
        proveedor.setEstado(p.getEstado());
        em.getTransaction().commit();
        em.close();
    }

}
