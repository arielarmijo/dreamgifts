package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import javax.persistence.EntityManager;

public class ProveedorDao extends AbstractSoftDeleteDao<Proveedor> {

    public ProveedorDao() {
        super(Proveedor.class);
    }

    public void updateEntity(Proveedor p) {
        Proveedor proveedor = findById(p.getId());
        proveedor.setRut(p.getRut());
        proveedor.setRazonSocial(p.getRazonSocial());
        proveedor.setContacto(p.getContacto());
        proveedor.setDireccion(p.getDireccion());
        proveedor.setComuna(p.getComuna());
        proveedor.setTelefono(p.getTelefono());
        proveedor.setEmail(p.getEmail());
        proveedor.setEstado(p.getEstado());
    }

}
