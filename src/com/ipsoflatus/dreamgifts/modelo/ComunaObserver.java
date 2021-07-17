package com.ipsoflatus.dreamgifts.modelo;

import com.ipsoflatus.dreamgifts.entidad.Comuna;
import java.util.List;

public interface ComunaObserver {
    
    void actualizarComunas(List<Comuna> comunas);
    
}
