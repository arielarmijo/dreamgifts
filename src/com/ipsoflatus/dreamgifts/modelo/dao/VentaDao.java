package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class VentaDao extends AbstractDao<Venta> {

    public VentaDao() {
        super(Persistence.createEntityManagerFactory("dreamgifts"), Venta.class);
    }

    @Override
    protected void setEstado(Venta t, boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void update(EntityManager em, Venta v) {
        Venta venta = em.find(Venta.class, v.getId());
        venta.setCliente(v.getCliente());
        venta.setTotal(v.getTotal());
        venta.setFechaVenta(v.getFechaVenta());
        venta.setFechaTransferencia(v.getFechaTransferencia());
        venta.setCodigoTransferencia(v.getCodigoTransferencia());
        venta.setBanco(v.getBanco());
        venta.setNombreDestinatario(v.getNombreDestinatario());
        venta.setApellidoDestinatario(v.getApellidoDestinatario());
        venta.setDireccionDestinatario(v.getDireccionDestinatario());
        venta.setComuna(v.getComuna());
        venta.setTelefonoDestinatario(v.getTelefonoDestinatario());
        venta.setFechaEntrega(v.getFechaEntrega());
        venta.setHoraEntregaInicial(v.getHoraEntregaInicial());
        venta.setHoraEntregaFinal(v.getHoraEntregaFinal());
        venta.setSaludo(v.getSaludo());
        venta.setRedSocial(v.getRedSocial());
        venta.setEstadoVenta(v.getEstadoVenta());
        venta.setPack(v.getPack());
    }

}
