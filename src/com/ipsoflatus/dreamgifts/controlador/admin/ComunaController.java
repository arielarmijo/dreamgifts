package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import java.util.List;

public class ComunaController implements ComunaObserver {

    private final ComunaService comunaSrv;
    private ComunaView view;

    public ComunaController() {
        this.comunaSrv = ComunaService.getInstance();
        this.comunaSrv.addObserver(this);
    }

    public void setView(ComunaView view) {
        this.view = view;
    }

    public void guardar(String nombre, String codigo) {
        comunaSrv.guardarComuna(new Comuna(nombre, codigo));
    }
    
    public void actualizarComunas() {
        actualizarComunas(comunaSrv.buscarComunas());
    }

    @Override
    public void actualizarComunas(List<Comuna> comunas) {
        view.actualizarTabla(comunas);
    }

}
