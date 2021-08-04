package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ArticuloTableModel;
import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.vista.admin.ArticuloView;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloController implements Controller<ArticuloView> {

    private final ArticuloService articuloService = ArticuloService.getInstance();
    private ArticuloTableModel tableModel;
    private Articulo articuloActual;
    private ArticuloView view;
    
    @Override
    public void setView(ArticuloView view) {
        this.view = view;
        this.tableModel = (ArticuloTableModel) view.getjTableArticulo().getModel();
    }

    @Override
    public void cancelar() {
        articuloActual = null;
        view.getTxfNombre().setText("");
        view.getTxfMarca().setText("");
        view.getCbxCategoria().setSelectedIndex(0);
        view.getjRadioButtonActivo().setSelected(true);
    }

    @Override
    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        String marca = view.getTxfMarca().getText();
        if (nombre.isEmpty() || marca.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        CategoriaArticulo categoria = (CategoriaArticulo) view.getCbxCategoria().getSelectedItem();
        if (categoria.getId() == null) {
            mostrarInformacion("Seleccione categoría.");
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
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
        String termino = view.getTxfBuscar().getText();
        List<Articulo> items = termino.isEmpty() ? articuloService.buscar(): articuloService.buscar(termino);
        tableModel.actualizar(items);
        view.getTxfBuscar().setText("");
    }

    @Override
    public void editar() {
        
        int row = view.getjTableArticulo().getSelectedRow();
        
        if (row == -1) {
            mostrarInformacion("Seleccione artículo.");
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

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione artículo(s)");
            return;
        }
        articuloService.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
