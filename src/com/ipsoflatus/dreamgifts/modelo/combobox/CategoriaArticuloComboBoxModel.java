package com.ipsoflatus.dreamgifts.modelo.combobox;

import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaArticuloComboBoxModel extends ObserverComboBoxModel<CategoriaArticulo> {

    public CategoriaArticuloComboBoxModel(ObservableService service) {
        super(service);
    }

    @Override
    public void actualizar(List<CategoriaArticulo> items) {
        System.out.println("CA CBX: " + items);
        CategoriaArticulo seleccioneCategoria = new CategoriaArticulo();
        seleccioneCategoria.setNombre("Seleccione categoría");
        seleccioneCategoria.setArticulos(ArticuloService.getInstance().buscar());
        List<CategoriaArticulo> categoriasActivas = items.stream().filter(c -> c.getEstado()).collect(Collectors.toList());
        categoriasActivas.add(0, seleccioneCategoria);
        updateModel(categoriasActivas.toArray());
    }
    
}
