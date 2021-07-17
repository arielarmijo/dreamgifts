package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import java.util.List;
import com.ipsoflatus.dreamgifts.modelo.Observer;

public class ComunaController implements Observer<Comuna> {

    private final ComunaService comunaSrv;
    private Comuna comunaActual;
    private ComunaView view;

    public ComunaController() {
        this.comunaSrv = ComunaService.getInstance();
        this.comunaSrv.addObserver(this);
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
            comunaSrv.editar(comunaActual.getId(), comunaActual);
        }
    }
    
    public void editar(String codigo) {
        comunaActual = comunaSrv.buscar(codigo);
    }
    
    public void actualizarComunas() {
        actualizar(comunaSrv.buscar());
    }

    @Override
    public void actualizar(List<Comuna> comunas) {
        view.actualizarTabla(comunas);
    }

}
