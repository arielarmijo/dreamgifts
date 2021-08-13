package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.EVService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.EVTableModel;
import com.ipsoflatus.dreamgifts.vista.admin.EstadoVentaView;
import java.util.List;
import java.util.stream.Collectors;

public class EVController {
    
      private final EVService service = EVService.getInstance();
      private final EstadoVentaView view;
      private final EVTableModel tableModel;
      private EstadoVenta evActual;

     
    public EVController(EstadoVentaView view) {
        this.view = view;
        this.tableModel = (EVTableModel) view.getjTableEV().getModel();
    }

    public void cancelar() {
        evActual = null;
        view.getjTextFieldCV().setText("");
        view.getjTextFieldIdCV().setText("");
        view.getjTextAreaDescripcion().setText("");
    }

    public void grabar() {
       String nombre = view.getjTextFieldCV().getText();
        String codigo = view.getjTextFieldIdCV().getText();
        String descripcion = view.getjTextAreaDescripcion().getText();
        System.out.println(descripcion);
                
        if (codigo.isEmpty() || nombre.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (evActual == null) {
                EstadoVenta ev = new EstadoVenta();
                ev.setCodigo(codigo);
                ev.setNombre(nombre);
                ev.setDescripcion(descripcion);
                ev.setEstado(Boolean.TRUE);
                service.guardar(ev);
            }else{
                evActual.setCodigo(codigo);
                evActual.setNombre(nombre);
                evActual.setDescripcion(descripcion);
                service.editar(evActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
    }

    public void buscar() {
         String termino = view.getjTextFieldBuscar().getText();
        List<EstadoVenta> eevv = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(eevv);
        view.getjTextFieldBuscar().setText("");
    }

    public void editar() {
          int row = view.getjTableEV().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione Categoria Venta.");
            return;
        }
        evActual = tableModel.getItem(row);
        view.getjTextFieldCV().setText(evActual.getNombre());
        view.getjTextFieldIdCV().setText(evActual.getCodigo());
        view.getjTextAreaDescripcion().setText(evActual.getDescripcion());
    }

    public void activarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Seleccione Categoria Venta");
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
