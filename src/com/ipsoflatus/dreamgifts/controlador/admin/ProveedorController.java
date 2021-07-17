package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ProveedorView;

public class ProveedorController {

    private final ComunaService comunaSrv;
    private ProveedorView view;
    
    public ProveedorController(ProveedorView view) {
        this.view = view;
        this.comunaSrv = ComunaService.getInstance();
    }

}
