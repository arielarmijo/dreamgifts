package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import com.ipsoflatus.dreamgifts.modelo.servicio.RedSocialService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.RedSocialTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.RRSSView;
import java.util.List;
import java.util.stream.Collectors;

public class RRSSController {

    private final RedSocialService redSocialSrv = RedSocialService.getInstance();;
    private final RRSSView view;
    private final RedSocialTableModel tableModel;
    private RedSocial redSocialActual;

    public RRSSController(RRSSView view) {
        this.view = view;
        this.tableModel = (RedSocialTableModel) view.getjTable().getModel();
    }

    public void cancelar() {
        redSocialActual = null;
        view.setCodigo("");
        view.setNombre("");
    }

    public void grabar(String codigo, String nombre) {

        if (nombre.isEmpty() || codigo.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (redSocialActual == null) {
                RedSocial rs = new RedSocial();
                rs.setCodigo(codigo);
                rs.setNombre(nombre);
                rs.setEstado(Boolean.TRUE);
                redSocialSrv.guardar(rs);
            } else {
                redSocialActual.setCodigo(codigo);
                redSocialActual.setNombre(nombre);
                redSocialSrv.editar(redSocialActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }

    }

    public void buscar(String termino) {
        List<RedSocial> rrss = termino.isEmpty() ? redSocialSrv.buscar() : redSocialSrv.buscar(termino);
        tableModel.actualizar(rrss);
        view.setBuscar("");
    }

    public void editar(String codigo) {
        int row = view.getjTable().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione Red social.");
            return;
        }
        redSocialActual = tableModel.getItem(row);
        view.setCodigo(redSocialActual.getCodigo());
        view.setNombre(redSocialActual.getNombre());
    }

    public void activarDesactivarSeleccionados(boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(ca -> ca.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Seleccione Red Social");
            return;
        }
        try {
            redSocialSrv.cambiarEstado(ids, estado);
            tableModel.selectAll(false);
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
    }

}
