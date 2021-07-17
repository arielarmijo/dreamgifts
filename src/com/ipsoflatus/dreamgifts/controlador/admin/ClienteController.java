package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.ComunaObserverController;
import com.ipsoflatus.dreamgifts.modelo.Comuna;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ClienteView;
import java.util.List;

public class ClienteController implements ComunaObserverController {
    
    private final ComunaService comunaSrv;
    private final ClienteView view;
    
    public ClienteController(ClienteView view) {
        this.view = view;
        this.comunaSrv = ComunaService.getInstance();
        this.comunaSrv.addObserver(this);
    }
    
    @Override
    public void actualizarComunas() {
        actualizarComunas(comunaSrv.buscarComunas());
    }

    @Override
    public void actualizarComunas(List<Comuna> comunas) {
        actualizarComunas(view, comunas);
    }
    
}
