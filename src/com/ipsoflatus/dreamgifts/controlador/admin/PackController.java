package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.lista.ArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.lista.PackHasArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.PackTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.PackView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class PackController {
    
    private final PackService packService;
    private final PackView view;
    private final ArticuloListModel articuloListModel;
    private final PackHasArticuloListModel packHasArticuloListModel;
    private final PackTableModel tableModel;
    private Pack packActual;
    
    public PackController(PackView view) {
        this.packService = PackService.getInstance();
        this.view = view;
        this.articuloListModel = (ArticuloListModel) view.getLstArticulo().getModel();
        this.packHasArticuloListModel = (PackHasArticuloListModel) view.getLstPackHasArticulo().getModel();
        this.tableModel = (PackTableModel) view.getjTable().getModel();
    }
    
    public void filtrarArticulo() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulo().getSelectedItem();
        if (ca != null) {
            System.out.println(ca + ": " + ca.getArticulos());
            articuloListModel.actualizar(ca.getArticulos());
        }
    }
    
    public void agregarArticuloPack() {
        Articulo articulo = view.getLstArticulo().getSelectedValue();
        if (articulo == null)
            return;
        PackHasArticulo pha = new PackHasArticulo();
        if (packActual != null)
            pha.setPack(packActual);
        pha.setArticulo(articulo);
        Integer cantidad = (Integer) view.getSpnCantidad().getValue();
        pha.setCantidad(cantidad);
        System.out.println(pha);
        packHasArticuloListModel.addItem(pha);
    }
    
    public void removerArticuloPack() {
        PackHasArticulo pha = view.getLstPackHasArticulo().getSelectedValue();
        System.out.println("remover " + pha);
        if (pha != null)
            packHasArticuloListModel.removeItem(pha);
    }

    public void cancelar() {
        packActual = null;
        view.getTxfNombre().setText("");
        view.getSpnPrecio().setValue(0);
        packHasArticuloListModel.actualizar(new ArrayList<>());
        view.getCbxCategoriaArticulo().setSelectedIndex(0);
        view.getLstArticulo().clearSelection();
        view.getSpnCantidad().setValue(1);
        view.getjRadioButtonActivo().setSelected(true);
    }

    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        if (nombre.isEmpty()) {
            mostrarInformacion("El pack debe tener un nombre.");
            return;
        }
        
        List<PackHasArticulo> articulos = packHasArticuloListModel.getItems();
        if (articulos.isEmpty()) {
            mostrarInformacion("El pack debe tener artículos.");
            return;
        }
        
        int precio = (int) view.getSpnPrecio().getValue();
        System.out.println("precio: $" + precio);
        Boolean estado = view.getButtonGroup().getSelection().getActionCommand().equals("Activo");

        try {
            if (packActual == null) {
                Pack pack = new Pack();
                pack.setNombre(nombre);
                pack.setCosto(precio);
                pack.setStock(0);
                pack.setEstado(estado);
                articulos.forEach(a -> {
                    a.setPack(pack);
                });
                pack.setArticulos(articulos);
                packService.guardar(pack);
                
            } else {
                packActual.setNombre(nombre);
                packActual.setCosto(precio);
                packActual.setEstado(estado);
                packActual.setArticulos(articulos);
                packService.editar(packActual);
            }
            cancelar();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError(e.getMessage());
        }
        
    }

    public void buscar() {
        String termino = view.getTxfBuscar().getText();
        List<Pack> packs = termino.isEmpty() ? packService.buscar(): packService.buscar(termino);
        tableModel.actualizar(packs);
        view.getTxfBuscar().setText("");
    }

    public void editar() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione pack.");
            return;
        }
        packActual = tableModel.getItem(row);
        view.getTxfNombre().setText(packActual.getNombre());
        view.getSpnPrecio().setValue(packActual.getCosto());
        view.getCbxCategoriaArticulo().setSelectedIndex(0);
        view.getLstArticulo().clearSelection();
        
        List<PackHasArticulo> items = packActual.getArticulos();
        packHasArticuloListModel.actualizar(items);
        
        if (packActual.getEstado())
            view.getjRadioButtonActivo().setSelected(true);
        else
            view.getjRadioButtonInactivo().setSelected(true);
    }

    
    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(Boolean.FALSE);
    }

    public void activarSeleccionados() {
        activarDesactivarSeleccionados(Boolean.TRUE);
    }
    
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione pack(s).");
            return;
        }
        packService.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
    }

    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
