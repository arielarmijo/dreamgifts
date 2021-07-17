package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import com.ipsoflatus.dreamgifts.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import java.util.List;

public class ComunaController implements ComunaObserver {

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
            comunaSrv.guardarComuna(new Comuna(nombre, codigo));
        } else {
            comunaActual.setNombre(nombre);
            comunaActual.setCodigo(codigo);
            comunaSrv.editarComuna(comunaActual.getId(), comunaActual);
        }
    }
    
    public void editar(String codigo) {
        comunaActual = comunaSrv.buscarComuna(codigo);
    }
    
    public void actualizarComunas() {
        actualizarComunas(comunaSrv.buscarComunas());
    }

    @Override
    public void actualizarComunas(List<Comuna> comunas) {
        view.actualizarTabla(comunas);
    }

}
