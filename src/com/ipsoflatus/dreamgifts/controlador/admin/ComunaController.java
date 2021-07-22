 package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.ComunaTableModel;
import com.ipsoflatus.dreamgifts.modelo.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import java.util.List;
import java.util.stream.Collectors;

public class ComunaController implements Controller<ComunaView> {

    private ComunaView view;
    private ComunaService service = ComunaService.getInstance();
    private ComunaTableModel tableModel;
    private Comuna comunaActual = null;
    
    @Override
    public void setView(ComunaView view) {
        this.view = view;
        this.tableModel = (ComunaTableModel) view.getjTable().getModel();
    }

    @Override
    public void cancelar() {
        view.getCodigoComuna().setText("");
        view.getNombreComuna().setText("");
        comunaActual = null;
    }

    @Override
    public void grabar() {
        String nombre = view.getNombreComuna().getText();
        String codigo = view.getCodigoComuna().getText();
        if (comunaActual == null) {
            Comuna comuna = new Comuna(nombre, codigo);
            service.guardar(comuna);
        } else {
            comunaActual.setCodigo(codigo);
            comunaActual.setNombre(nombre);
            service.editar(comunaActual);
        }
        cancelar();
    }

    @Override
    public void buscar() {
        String termino = view.getBuscar().getText();
        List<Comuna> comunas;
        if (termino.isEmpty()) {
            comunas = service.buscar();
        } else {
            comunas = service.buscar(termino);
        }
        
        tableModel.actualizar(comunas);
        view.getBuscar().setText("");
    }

    @Override
    public void editar() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione categoría.");
            return;
        }
        comunaActual = tableModel.getItem(row);
        view.getCodigoComuna().setText(comunaActual.getCodigo());
        view.getNombreComuna().setText(comunaActual.getNombre());
        
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Selecciones comunas");
            return;
        }
        service.cambiarEstado(ids, estado);
        tableModel.selectAll(false);
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
