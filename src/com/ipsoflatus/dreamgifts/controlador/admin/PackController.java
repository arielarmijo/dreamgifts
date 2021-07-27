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
import com.ipsoflatus.dreamgifts.vista.admin.PackView;
import java.util.ArrayList;
import java.util.List;

public class PackController implements Controller<PackView> {
    
    private final ArticuloService articuloService = ArticuloService.getInstance();
    private final PackService packService = PackService.getInstance();
    private PackView view;
    private ArticuloListModel articuloListModel;
    private PackHasArticuloListModel packHasArticuloListModel;
    private Pack packActual;
    
    public void filtrarArticulo() {
        CategoriaArticulo ca = (CategoriaArticulo) view.getCbxCategoriaArticulo().getSelectedItem();
        System.out.println(ca.getId());
        List<Articulo> articulos = articuloService.buscarPorCategoria(ca.getId());
        System.out.println(articulos);
        articuloListModel.actualizar(articulos);
    }
    
    public void agregarArticuloPack() {
        Articulo articulo = view.getLstArticulo().getSelectedValue();
        Integer cantidad = (Integer) view.getSpnCantidad().getValue();
        if (articulo == null) {
            mostrarInformacion("Seleccione artículo");
            return;
        }
        PackHasArticulo pha = new PackHasArticulo(null, articulo.getId(), cantidad);
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
    }

    @Override
    public void cancelar() {
        packActual = null;
        view.getTxfNombre().setText("");
        view.getTxfPrecio().setText("");
        packHasArticuloListModel.actualizar(new ArrayList<>());
        view.getCbxCategoriaArticulo().setSelectedIndex(0);
        view.getSpnCantidad().setValue(1);
        view.getjRadioButtonActivo().setSelected(true);
    }

    @Override
    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        int precio = Integer.parseInt(view.getTxfPrecio().getText());
        Boolean estado = view.getButtonGroup().getSelection().getActionCommand().equals("Activo");
        List<PackHasArticulo> articulos = packHasArticuloListModel.getItems();

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

            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
