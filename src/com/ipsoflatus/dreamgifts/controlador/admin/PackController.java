package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.list.ArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.list.PackHasArticuloListModel;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.table.PackTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.PackView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackController implements Controller<PackView> {
    
    private final ArticuloService articuloService = ArticuloService.getInstance();
    private final PackService packService = PackService.getInstance();
    private PackView view;
    private ArticuloListModel articuloListModel;
    private PackHasArticuloListModel packHasArticuloListModel;
    private PackTableModel tableModel;
    private Pack packActual;
    
    public void filtrarArticulo() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulo().getSelectedItem();
        if (ca != null) {
           System.out.println(ca.getId());
           List<Articulo> articulos = articuloService.buscarPorCategoria(ca.getId());
           System.out.println(articulos);
           articuloListModel.actualizar(articulos);
        }
        
    }
    
    public void agregarArticuloPack() {
        Articulo articulo = view.getLstArticulo().getSelectedValue();
        Integer cantidad = (Integer) view.getSpnCantidad().getValue();
        if (articulo == null) {
            mostrarInformacion("Seleccione artículo");
            return;
        }
        Integer packId = packActual != null ? packActual.getId() : null;
        PackHasArticulo pha = new PackHasArticulo(packId, articulo.getId(), cantidad);
        System.out.println(pha);
        packHasArticuloListModel.addItem(pha);
    }
    
    public void removerArticuloPack() {
        PackHasArticulo pha = view.getLstPackHasArticulo().getSelectedValue();
        System.out.println(pha);
        if (pha != null)
            packHasArticuloListModel.removeItem(pha);
    }

    @Override
    public void setView(PackView view) {
        this.view = view;
        this.articuloListModel = (ArticuloListModel) view.getLstArticulo().getModel();
        this.packHasArticuloListModel = (PackHasArticuloListModel) view.getLstPackHasArticulo().getModel();
        this.tableModel = (PackTableModel) view.getjTable().getModel();
    }

    @Override
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

    @Override
    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        Integer precio = (Integer) view.getSpnPrecio().getValue();
        Boolean estado = view.getButtonGroup().getSelection().getActionCommand().equals("Activo");
        List<PackHasArticulo> articulos = packHasArticuloListModel.getItems();
        
        if (nombre.isEmpty()) {
            mostrarInformacion("El pack debe tener un nombre.");
            return;
        }
        
        if (articulos.isEmpty()) {
            mostrarInformacion("El pack debe tener artículos.");
            return;
        }

        try {
            if (packActual == null) {
                Pack pack = new Pack();
                pack.setNombre(nombre);
                pack.setCosto(precio);
                pack.setStock(0);
                pack.setEstado(estado);
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
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
        
    }

    @Override
    public void buscar() {
        String termino = view.getTxfBuscar().getText();
        List<Pack> packs = termino.isEmpty() ? packService.buscar(): packService.buscar(termino);
        tableModel.actualizar(packs);
    }

    @Override
    public void editar() {
        int row = view.getjTable().getSelectedRow();
        
        if (row == -1) {
            mostrarInformacion("Seleccione pack.");
            return;
        }
        
        packActual = tableModel.getItem(row);
        System.out.println(packActual.getArticulos());
        view.getTxfNombre().setText(packActual.getNombre());
        view.getSpnPrecio().setValue(packActual.getCosto());
        view.getCbxCategoriaArticulo().setSelectedIndex(0);
        packHasArticuloListModel.actualizar(packActual.getArticulos());
        if (packActual.getEstado())
            view.getjRadioButtonActivo().setSelected(true);
        else
            view.getjRadioButtonInactivo().setSelected(true);
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione pack(s).");
            return;
        }
        packService.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
