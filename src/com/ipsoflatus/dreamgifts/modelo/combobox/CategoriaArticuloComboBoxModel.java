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
        CategoriaArticulo seleccioneComuna = new CategoriaArticulo(null, "Seleccione categoría");
        categoriasActivas.add(0, seleccioneComuna);
        updateModel(categoriasActivas.toArray());
    }
    
}
