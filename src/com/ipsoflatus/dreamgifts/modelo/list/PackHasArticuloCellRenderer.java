package com.ipsoflatus.dreamgifts.modelo.list;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PackHasArticuloCellRenderer extends JLabel implements ListCellRenderer<PackHasArticulo> {

    private final ArticuloService articuloService = ArticuloService.getInstance();
    
    public PackHasArticuloCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends PackHasArticulo> list, PackHasArticulo value, int index, boolean isSelected, boolean cellHasFocus) {
        Articulo articulo = articuloService.buscar(value.getArticuloId());
        setText(value.getCantidad() + " X " + articulo.getNombre());
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
