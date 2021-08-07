package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.FacturaDetalleDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.FacturaDetalle;
import java.util.Date;
import java.util.List;

public class FacturaDetalleService extends AbstractService<FacturaDetalle> {

    private static FacturaDetalleService instance;

    private FacturaDetalleService() {
        super(new FacturaDetalleDao());
    }
    
    public static FacturaDetalleService getInstance() {
        if (instance == null)
            instance = new FacturaDetalleService();
        return instance;
    }
    
    public List<FacturaDetalle> filtrarPorFecha(Date desde, Date hasta) {
        return ((FacturaDetalleDao) dao).findByDateBetween(desde, hasta);
    }
    
    public List<FacturaDetalle> filtrarPorFechaDesde(Date desde) {
        return ((FacturaDetalleDao) dao).findByDateGreaterThan(desde);
    }
    
    public List<FacturaDetalle> filtrarPorFechaHasta(Date hasta) {
        return ((FacturaDetalleDao) dao).findByDateSmallerThan(hasta);
    }

}
