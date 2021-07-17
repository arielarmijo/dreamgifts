package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;

public class VentaController {
    
    private final ComunaService comunaSrv;
    private final VentaView view;
    
    public VentaController(VentaView view) {
        this.view = view;
        this.comunaSrv = ComunaService.getInstance();
    }

}
