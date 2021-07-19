 package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;

public class ComunaController {

    private final ComunaService comunaSrv;
    private Comuna comunaActual;
    private ComunaView view;

    public ComunaController() {
        this.comunaSrv = ComunaService.getInstance();
        comunaActual = null;
    }

    public void setView(ComunaView view) {
        this.view = view;
    }

    public void guardar(String nombre, String codigo) {
        if (comunaActual == null) {
            comunaSrv.guardar(new Comuna(nombre, codigo));
        } else {
            comunaActual.setNombre(nombre);
            comunaActual.setCodigo(codigo);
            comunaSrv.editar(comunaActual);
        }
    }
    
    public void editar(String codigo) {
        throw new UnsupportedOperationException();
    }
    
    public void actualizarComunas() {

    }

}
