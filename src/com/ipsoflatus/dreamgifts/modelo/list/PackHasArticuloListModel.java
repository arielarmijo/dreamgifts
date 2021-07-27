package com.ipsoflatus.dreamgifts.modelo.list;

import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

public class PackHasArticuloListModel extends AbstractListModel<PackHasArticulo> {

    private List<PackHasArticulo> items;
    
    public PackHasArticuloListModel() {
        this.items = new ArrayList<>();
    }
   
    public void addItem(PackHasArticulo pha) {
        if (items.contains(pha)) {
            PackHasArticulo tmp = items.get(items.indexOf(pha));
            tmp.setCantidad(tmp.getCantidad() + pha.getCantidad());
        } else {
           items.add(pha); 
        }
        fireIntervalAdded(this, items.size(), items.size());
    }
    
    public void removeItem(PackHasArticulo pha) {
        fireIntervalRemoved(this, items.indexOf(pha), items.indexOf(pha));
        items.remove(pha);
    }
    
    public List<PackHasArticulo> getItems() {
        return items;
    }
    
    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public PackHasArticulo getElementAt(int index) {
        return items.get(index);
    }

    public void actualizar(List<PackHasArticulo> items) {
        this.items = items;
        fireContentsChanged(this, 0, items.size());
    }

}
