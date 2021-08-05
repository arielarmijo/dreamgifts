package com.ipsoflatus.dreamgifts.modelo.lista;

import com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class OrdenCompraDetalleCellRenderer extends JLabel implements ListCellRenderer<OrdenCompraDetalle> {

    public OrdenCompraDetalleCellRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends OrdenCompraDetalle> list, OrdenCompraDetalle value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null)
            setText(value.getCantidad() + " X " + value.getArticulo().getNombre());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
