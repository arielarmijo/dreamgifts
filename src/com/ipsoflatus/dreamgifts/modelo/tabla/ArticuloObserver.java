package com.ipsoflatus.dreamgifts.modelo.tabla;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import java.util.List;

public interface ArticuloObserver extends Observer {
    
    void actualizarArticulo(List<Articulo> items);

}
