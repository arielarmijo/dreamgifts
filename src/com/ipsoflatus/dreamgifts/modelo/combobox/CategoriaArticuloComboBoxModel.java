package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaArticuloComboBoxModel extends ObserverComboBoxModel<CategoriaArticulo> {

    public CategoriaArticuloComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<CategoriaArticulo> items) {
        List<CategoriaArticulo> categoriasActivas = items.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        CategoriaArticulo seleccioneCategoria = new CategoriaArticulo();
        seleccioneCategoria.setNombre("Seleccione categoría");
        categoriasActivas.add(0, seleccioneCategoria);
        updateModel(categoriasActivas.toArray());
    }
    
}
