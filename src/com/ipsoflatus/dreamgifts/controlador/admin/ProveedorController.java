package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ProveedorView;
import java.util.ArrayList;
import java.util.List;

public class ProveedorController implements ComunaObserver {

    private final ComunaService comunaSrv;
    private ProveedorView view;
    
    public ProveedorController() {
        this.comunaSrv = ComunaService.getInstance();
        this.comunaSrv.addObserver(this);
    }
    
    public void setView(ProveedorView view) {
        this.view = view;
    }

    @Override
    public void actualizarComunas(List<Comuna> comunas) {
        List<String> nombreComunas = new ArrayList<>();
        nombreComunas.add("Seleccione comuna");
        comunas.forEach(c -> {
            nombreComunas.add(c.getNombre());
        });
        view.setComboBoxComunaModel(nombreComunas.toArray());
    }

    public void actualizarComunas() {
        actualizarComunas(comunaSrv.buscarComunas());
    }
    
}
