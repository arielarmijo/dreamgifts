package com.ipsoflatus.dreamgifts.controlador;

import com.ipsoflatus.dreamgifts.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.ComunaObserver;
import com.ipsoflatus.dreamgifts.vista.ComunaComboBoxView;
import java.util.List;
import java.util.stream.Collectors;

public interface ComunaObserverController extends ComunaObserver {
    
    void actualizarComunas();

    default void actualizarComunas(ComunaComboBoxView view, List<Comuna> comunas) {
        Comuna seleccionarComuna = new Comuna(-1, "Seleccione comuna", null, true);
        comunas.add(0, seleccionarComuna);
        List<Comuna> comunasActivas = comunas.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        view.setComboBoxComunaModel(comunasActivas.toArray());
    }
    
}
