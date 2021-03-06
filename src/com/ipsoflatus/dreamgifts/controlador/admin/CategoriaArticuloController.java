package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.tabla.admin.CategoriaArticuloTableModel;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.CategoriaArticuloService;
import com.ipsoflatus.dreamgifts.vista.admin.CategoriaArticuloView;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaArticuloController {

    private final CategoriaArticuloService service = CategoriaArticuloService.getInstance();
    private final CategoriaArticuloView view;
    private final CategoriaArticuloTableModel tableModel;
    private CategoriaArticulo categoriaActual;

    public CategoriaArticuloController(CategoriaArticuloView view) {
        this.view = view;
        this.tableModel = (CategoriaArticuloTableModel) view.getjTableCA().getModel();
    }
    
    public void cancelar() {
        categoriaActual = null;
        view.getjTextFieldCodigo().setText("");
        view.getjTextFieldNombre().setText("");
    }

    public void grabar() {
        String codigo = view.getjTextFieldCodigo().getText();
        String nombre = view.getjTextFieldNombre().getText();
        if (codigo.isEmpty() || nombre.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        Boolean estado = view.getButtonGroupEstado().getSelection().getActionCommand().equals("Activo");
        try {
            if (categoriaActual == null) {
                CategoriaArticulo ca = new CategoriaArticulo();
                ca.setCodigo(codigo);
                ca.setNombre(nombre);
                ca.setEstado(estado);
                service.guardar(ca);
            } else {
                categoriaActual.setCodigo(codigo);
                categoriaActual.setNombre(nombre);
                categoriaActual.setEstado(estado);
                service.editar(categoriaActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }
    
    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<CategoriaArticulo> ccaa = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(ccaa);
        view.getjTextFieldBuscar().setText("");
    }

    public void editar() {      
        int row = view.getjTableCA().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione categor??a.");
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
    
    public void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }
    
    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }
    
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Selecciones categor??as");
            return;
        }
        try {
            service.cambiarEstado(ids, estado);service.cambiarEstado(ids, estado);
            tableModel.selectAll(false);
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
        
    }

}
