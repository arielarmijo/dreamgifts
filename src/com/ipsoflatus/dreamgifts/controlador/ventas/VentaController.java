package com.ipsoflatus.dreamgifts.controlador.ventas;

import com.ipsoflatus.dreamgifts.controlador.ComunaObserverController;
import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;
import java.util.List;

public class VentaController implements ComunaObserverController {
    
    private final ComunaService comunaSrv;
    private final VentaView view;
    
    public VentaController(VentaView view) {
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
