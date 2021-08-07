package com.ipsoflatus.dreamgifts.modelo.lista;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ObservableService;
import java.util.List;
import javax.swing.AbstractListModel;

public class ArticuloListModel extends AbstractListModel<Articulo> implements Observer<Articulo> {
    
    private final ObservableService service;
    private List<Articulo> items;
    
    public ArticuloListModel() {
        this.service = ArticuloService.getInstance();
        this.service.addObserver(this);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Articulo getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void actualizar(List<Articulo> items) {
        this.items = items;
        fireContentsChanged(this, 0, items.size());
    }

}
