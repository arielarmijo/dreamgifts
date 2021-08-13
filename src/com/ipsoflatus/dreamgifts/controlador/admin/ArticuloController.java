package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ArticuloTableModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.vista.admin.ArticuloView;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloController {

    private final ArticuloService articuloService = ArticuloService.getInstance();
    private final ArticuloView view;
    private final ArticuloTableModel tableModel;
    private Articulo articuloActual;
    
    public ArticuloController(ArticuloView view) {
        this.view = view;
        this.tableModel = (ArticuloTableModel) view.getjTableArticulo().getModel();
    }

    public void cancelar() {
        articuloActual = null;
        view.getTxfNombre().setText("");
        view.getTxfMarca().setText("");
        view.getCbxCategoria().setSelectedIndex(0);
        view.getjRadioButtonActivo().setSelected(true);
    }

    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        String marca = view.getTxfMarca().getText();
        if (nombre.isEmpty() || marca.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        CategoriaArticulo categoria = (CategoriaArticulo) view.getCbxCategoria().getSelectedItem();
        if (categoria.getId() == null) {
            view.mostrarInformacion("Seleccione categoría.");
            return;
        }
        
        Boolean estado = view.getButtonGroup().getSelection().getActionCommand().equals("Activo");

        try {
            if (articuloActual == null) {
                Articulo articulo = new Articulo();
                articulo.setNombre(nombre);
                articulo.setMarca(marca);
                articulo.setStock(0);
                articulo.setCategoriaArticulo(categoria);
                articulo.setEstado(estado);
                articuloService.guardar(articulo);
            } else {
                articuloActual.setNombre(nombre);
                articuloActual.setMarca(marca);
                articuloActual.setCategoriaArticulo(categoria);
                articuloActual.setEstado(estado);
                articuloService.editar(articuloActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }

    public void buscar() {
        String termino = view.getTxfBuscar().getText();
        List<Articulo> items = termino.isEmpty() ? articuloService.buscar(): articuloService.buscar(termino);
        tableModel.actualizar(items);
        view.getTxfBuscar().setText("");
    }

    public void editar() {
        
        int row = view.getjTableArticulo().getSelectedRow();
        
        if (row == -1) {
            view.mostrarInformacion("Seleccione artículo.");
            return;
        }
        
        articuloActual = tableModel.getItem(row);
        view.getTxfNombre().setText(articuloActual.getNombre());
        view.getTxfMarca().setText(articuloActual.getMarca());
        view.getCbxCategoria().getModel().setSelectedItem(articuloActual.getCategoriaArticulo());
        
        if (articuloActual.getEstado())
            view.getjRadioButtonActivo().setSelected(true);
        else
            view.getjRadioButtonInactivo().setSelected(true);
        
    }

    public void activarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Seleccione artículo(s)");
            return;
        }
        try {
            articuloService.cambiarEstado(ids, estado);
            tableModel.selectAll(false);
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
    }

}
