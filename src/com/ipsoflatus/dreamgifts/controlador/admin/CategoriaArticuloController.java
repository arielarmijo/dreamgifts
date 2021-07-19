package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.CategoriaArticuloTableModel;
import com.ipsoflatus.dreamgifts.modelo.Controller;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import com.ipsoflatus.dreamgifts.vista.admin.CategoriaArticuloView;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaArticuloController implements Controller<CategoriaArticuloView> {

    private final CategoriaArticuloService service;
    private CategoriaArticulo categoriaActual;
    private CategoriaArticuloTableModel tableModel;
    private CategoriaArticuloView view;

    public CategoriaArticuloController() {
        service = CategoriaArticuloService.getInstance();
        categoriaActual = null;
    }

    @Override
    public void setView(CategoriaArticuloView view) {
        this.view = view;
        this.tableModel = (CategoriaArticuloTableModel) view.getjTableCA().getModel();
    }
    
    @Override
    public void cancelar() {
        categoriaActual = null;
        view.getjTextFieldCodigo().setText("");
        view.getjTextFieldNombre().setText("");
    }

    @Override
    public void grabar() {
        
        String codigo = view.getjTextFieldCodigo().getText();
        String nombre = view.getjTextFieldNombre().getText();
        Boolean estado = view.getButtonGroupEstado().getSelection().getActionCommand().equals("Activo");
                
        if (codigo.isEmpty() || nombre.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (categoriaActual == null) {
                service.guardar(new CategoriaArticulo(codigo, nombre, estado));
            } else {
                categoriaActual.setCodigo(codigo);
                categoriaActual.setNombre(nombre);
                categoriaActual.setEstado(estado);
                service.editar(categoriaActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
        
    }
    
    @Override
    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<CategoriaArticulo> ccaa = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(ccaa);
    }

    @Override
    public void editar() {      
        int row = view.getjTableCA().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione categoría.");
            return;
        }
        categoriaActual = tableModel.getItem(row);
        view.getjTextFieldCodigo().setText(categoriaActual.getCodigo());
        view.getjTextFieldNombre().setText(categoriaActual.getNombre());
        
        if (categoriaActual.getEstado())
            view.getjRadioButtonActivo().setSelected(true);
        else
            view.getjRadioButtonInactivo().setSelected(true);
    }
    
    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Selecciones categorías");
            return;
        }
        service.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
        view.getjToggleButton().setText("Seleccionar todos");
        view.getjToggleButton().setSelected(false);
        
    }

    @Override
    public void seleccionarTodos() {
        boolean select = view.getjToggleButton().isSelected();
        tableModel.selectAll(select);
        String text = select ? "Deseleccionar todos" : "Seleccionar todos";
        view.getjToggleButton().setText(text);
    }

}
