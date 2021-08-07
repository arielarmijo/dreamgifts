package com.ipsoflatus.dreamgifts.modelo.lista;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

public class OrdenCompraDetalleListModel extends AbstractListModel<OrdenCompraDetalle> {

    private List<OrdenCompraDetalle> items;
    
    public OrdenCompraDetalleListModel() {
        this.items = new ArrayList<>();
    }
    
    public void actualizar(List<OrdenCompraDetalle> items) {
        this.items = items;
        fireContentsChanged(this, 0, items.size());
    }
    
    public List<OrdenCompraDetalle> getItems() {
        return items;
    }
   
    public void addItem(OrdenCompraDetalle ocd) {
        if (items.contains(ocd)) {
            OrdenCompraDetalle tmp = items.get(items.indexOf(ocd));
            tmp.setCantidad(tmp.getCantidad() + ocd.getCantidad());
        } else {
           items.add(ocd); 
        }
        fireIntervalAdded(this, items.size(), items.size());
    }
    
    public void removeItem(OrdenCompraDetalle ocd) {
        fireIntervalRemoved(this, items.indexOf(ocd), items.indexOf(ocd));
        items.remove(ocd);
    }
    
    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public OrdenCompraDetalle getElementAt(int index) {
        return items.get(index);
    }

}
