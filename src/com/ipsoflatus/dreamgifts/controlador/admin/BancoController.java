package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.tabla.admin.BancoTableModel;
import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.BancoService;
import com.ipsoflatus.dreamgifts.vista.admin.BancoView;
import java.util.List;
import java.util.stream.Collectors;

public class BancoController {
    
    private final BancoService service = BancoService.getInstance();
    private final BancoView view;
    private final BancoTableModel tableModel;
    private Banco bancoActual;
     
      
    public BancoController(BancoView view){
        this.view = view;
        this.tableModel = (BancoTableModel) view.getjTableBanco().getModel();
    }

    public void cancelar() {
        bancoActual = null;
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

        try {
            if (bancoActual == null) {
               service.guardar(new Banco(null, codigo, nombre, true));
            }else{
                bancoActual.setCodigo(codigo);
                bancoActual.setNombre(nombre);
                service.editar(bancoActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
        
    }

    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<Banco> bancos = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(bancos);
        view.getjTextFieldBuscar().setText("");
    }

    public void editar() {
        int row = view.getjTableBanco().getSelectedRow();
        if (row == -1) {
            view.mostrarInformacion("Seleccione Banco.");
            return;
        }
        bancoActual = tableModel.getItem(row);
        view.getjTextFieldCodigo().setText(bancoActual.getCodigo());
        view.getjTextFieldNombre().setText(bancoActual.getNombre());
    }

    public void activarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            view.mostrarInformacion("Seleccione banco");
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
