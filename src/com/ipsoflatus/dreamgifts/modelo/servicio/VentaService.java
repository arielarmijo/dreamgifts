package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.FacturaDao;
import com.ipsoflatus.dreamgifts.modelo.dao.VentaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import java.util.Date;
import java.util.List;

public class VentaService extends AbstractService<Venta> {
    
    private static VentaService instance;

    private VentaService() {
        super(new VentaDao());
    }
    
    public static VentaService getInstance() {
        if (instance == null)
            instance = new VentaService();
        return instance;
    } 

    public Date obtenerFechaMinima() {
        return ((VentaDao) dao).findMinDate();
    }
    
    public Date obtenerFechaMaxima() {
        return ((VentaDao) dao).findMaxDate();
    }

    public List<Venta> filtrarPorFecha(Date desde, Date hasta) {
        return ((VentaDao) dao).findByDateBetween(desde, hasta);
    }
    
}
