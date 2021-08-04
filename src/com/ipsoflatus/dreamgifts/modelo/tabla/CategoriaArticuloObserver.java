package com.ipsoflatus.dreamgifts.modelo.tabla;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.List;

public interface CategoriaArticuloObserver extends Observer {
    
    void actualizarCategoriaArticulo(List<CategoriaArticulo> items);
    
}
