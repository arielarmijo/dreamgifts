package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import javax.persistence.EntityManager;

public class EstadoVentaDao extends AbstractSoftDeleteDao<EstadoVenta> {

    public EstadoVentaDao() {
        super(EstadoVenta.class);
    }

    public void updateEntity(EstadoVenta ev) {
        EstadoVenta estadoVenta = findById(ev.getId());
        estadoVenta.setCodigo(ev.getCodigo());
        estadoVenta.setNombre(ev.getNombre());
        estadoVenta.setDescripcion(ev.getDescripcion());
        estadoVenta.setEstado(ev.getEstado());
    }

}
