package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloComboBoxModel extends ObserverComboBoxModel<Articulo>  {

    public ArticuloComboBoxModel() {
        super(ArticuloService.getInstance());
    }
    
    @Override
    public void actualizar(List<Articulo> items) {
        Articulo seleccioneArticulo = new Articulo();
        seleccioneArticulo.setNombre("Seleccione artículo");
        List<Articulo> articulosActivos = items.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        articulosActivos.add(0, seleccioneArticulo);
        updateModel(articulosActivos.toArray());
    }
    
}
