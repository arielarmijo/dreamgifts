package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ClienteView;

public class ClienteController {
    
    private final ComunaService comunaSrv;
    private final ClienteView view;
    
    public ClienteController(ClienteView view) {
        this.view = view;
        this.comunaSrv = ComunaService.getInstance();
    }
    
}
