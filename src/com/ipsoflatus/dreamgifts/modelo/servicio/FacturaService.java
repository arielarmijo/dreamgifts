package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.FacturaDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Factura;
import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import java.util.Date;
import java.util.List;

public class FacturaService extends AbstractService<Factura> {

    private static FacturaService instance;

    private FacturaService() {
        super(new FacturaDao());
    }
    
    public static FacturaService getInstance() {
        if (instance == null)
            instance = new FacturaService();
        return instance;
    }
    
    public Factura buscarPorNumeroFactura(Integer numeroFactura) {
        return ((FacturaDao) dao).findByNumber(numeroFactura);
    }
    
    public List<Factura> buscarPorProveedor(List<Proveedor> proveedores) {
        return ((FacturaDao) dao).findByProveedor(proveedores);
    }
    
    public List<Factura> buscarPorFecha(Date desde, Date hasta) {
        return ((FacturaDao) dao).findByDateBetween(desde, hasta);
    }

    public Date obtenerFechaMinima() {
        return ((FacturaDao) dao).findMinDate();
    }
    
    public Date obtenerFechaMaxima() {
        return ((FacturaDao) dao).findMaxDate();
    }
    
}
