package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ComunaTableModel;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import java.util.List;
import java.util.stream.Collectors;

public class ComunaController {

    private final ComunaService service = ComunaService.getInstance();
    private final ComunaView view;
    private final ComunaTableModel tableModel;
    private Comuna comunaActual;

    public ComunaController(ComunaView view) {
        this.view = view;
        this.tableModel = (ComunaTableModel) view.getjTable().getModel();
    }

    public void cancelar() {
        view.getCodigoComuna().setText("");
        view.getNombreComuna().setText("");
        comunaActual = null;
    }

    public void grabar() {

        String nombre = view.getNombreComuna().getText();
        if (nombre.isEmpty()) {
            view.mostrarInformacion("Ingrese nombre de la comuna.");
            return;
        }

        String codigo = view.getCodigoComuna().getText();
        if (codigo.isEmpty()) {
            view.mostrarInformacion("Ingrese código de la comuna");
            return;
        }

        try {
            if (comunaActual == null) {
            Comuna comuna = new Comuna();
            comuna.setCodigo(codigo);
            comuna.setNombre(nombre);
            comuna.setEstado(Boolean.TRUE);
            service.guardar(comuna);
            } else {
                comunaActual.setCodigo(codigo);
                comunaActual.setNombre(nombre);
                service.editar(comunaActual);
            }
            cancelar();
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
        
    }

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

    public void editar() {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione categoría.");
            return;
        }
        comunaActual = tableModel.getItem(row);
        view.getCodigoComuna().setText(comunaActual.getCodigo());
        view.getNombreComuna().setText(comunaActual.getNombre());

    }

    public void activarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Selecciones comunas");
            return;
        }
        try {
            service.cambiarEstado(ids, estado);
            tableModel.selectAll(false);
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
        
    }

}
