package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.table.admin.ArticuloTableModel;
import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.ArticuloService;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import com.ipsoflatus.dreamgifts.vista.admin.ArticuloView;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloController implements Controller<ArticuloView> {

    private final ArticuloService articuloService = ArticuloService.getInstance();
    private final CategoriaArticuloService categoriaArticuloService = CategoriaArticuloService.getInstance();
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
        view.getCbxComuna().setSelectedIndex(0);
        view.getjRadioButtonActivo().setSelected(true);
    }

    @Override
    public void grabar() {
        
        String nombre = view.getTxfNombre().getText();
        String marca = view.getTxfMarca().getText();
        Boolean estado = view.getButtonGroup().getSelection().getActionCommand().equals("Activo");
        CategoriaArticulo categoria = (CategoriaArticulo) view.getCbxComuna().getSelectedItem();
        System.out.println("Categoria id: " + categoria.getId());
                
        if (nombre.isEmpty() || marca.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        if (categoria.getId() == null) {
            mostrarInformacion("Seleccione categoría.");
            return;
        }

        try {
            if (articuloActual == null) {
                articuloService.guardar(new Articulo(nombre, marca, categoria.getId(), estado));
            } else {
                articuloActual.setNombre(nombre);
                articuloActual.setMarca(marca);
                articuloActual.setCategoriaId(categoria.getId());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        CategoriaArticulo ca = categoriaArticuloService.buscar(articuloActual.getCategoriaId());
        System.out.println(articuloActual.getNombre() + " " + articuloActual.getId() + " " + articuloActual.getCategoriaId());
        System.out.println(ca.getNombre() + " " + ca.getId());
        view.getCbxComuna().getModel().setSelectedItem(ca);
        articuloActual.setCategoriaId(ca.getId());
        
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
