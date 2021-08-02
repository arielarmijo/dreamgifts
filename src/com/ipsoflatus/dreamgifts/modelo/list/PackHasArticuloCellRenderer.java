package com.ipsoflatus.dreamgifts.modelo.list;

import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PackHasArticuloCellRenderer extends JLabel implements ListCellRenderer<PackHasArticulo> {
    
    public PackHasArticuloCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends PackHasArticulo> list, PackHasArticulo value, int index, boolean isSelected, boolean cellHasFocus) {
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
