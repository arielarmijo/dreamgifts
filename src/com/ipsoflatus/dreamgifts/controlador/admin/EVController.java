/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.table.EVTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.EstadoVentaView;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
public class EVController implements Controller<EstadoVentaView>{
    
    private EstadoVentaView view;
      private EstadoVenta evActual = null;
      private EVTableModel tableModel;
      private final EVService service = EVService.getInstance();

     
    @Override
    public void setView(EstadoVentaView view) {
        this.view = view;
        this.tableModel = (EVTableModel) view.getjTableEV().getModel();
    }

    @Override
    public void cancelar() {
        evActual = null;
        view.getjTextFieldCV().setText("");
        view.getjTextFieldIdCV().setText("");
        view.getjTextAreaDescripcion().setText("");
    }

    @Override
    public void grabar() {
       String nombre = view.getjTextFieldCV().getText();
        String codigo = view.getjTextFieldIdCV().getText();
        String descripcion = view.getjTextAreaDescripcion().getText();
                
        if (codigo.isEmpty() || nombre.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (evActual == null) {
               service.guardar(new EstadoVenta(codigo, nombre, descripcion, true));
            }else{
                evActual.setCodigo(codigo);
                evActual.setNombre(nombre);
                evActual.setDescripcion(descripcion);
                service.editar(evActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
         String termino = view.getjTextFieldBuscar().getText();
        List<EstadoVenta> eevv = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(eevv);
        view.getjTextFieldBuscar().setText("");
    }

    @Override
    public void editar() {
          int row = view.getjTableEV().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione Categoria Venta.");
            return;
        }
        evActual = tableModel.getItem(row);
        view.getjTextFieldCV().setText(evActual.getNombre());
        view.getjTextFieldIdCV().setText(evActual.getCodigo());
        view.getjTextAreaDescripcion().setText(evActual.getDescripcion());
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione Categoria Venta");
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
