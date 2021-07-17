package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.ComunaObserverController;
import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ProveedorView;
import java.util.List;

public class ProveedorController implements ComunaObserverController {

    private final ComunaService comunaSrv;
    private ProveedorView view;
    
    public ProveedorController(ProveedorView view) {
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
