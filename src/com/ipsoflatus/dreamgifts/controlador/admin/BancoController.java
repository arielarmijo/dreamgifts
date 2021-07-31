package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.table.admin.BancoTableModel;
import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.servicio.BancoService;
import com.ipsoflatus.dreamgifts.vista.admin.BancoView;
import java.util.List;
import java.util.stream.Collectors;

public class BancoController implements Controller<BancoView> {
    private BancoView view;
      private Banco bancoActual = null;
      private BancoTableModel tableModel;
      private final BancoService service = BancoService.getInstance();
     
      
    @Override
    public void setView(BancoView view){
      this.view = view;
        this.tableModel = (BancoTableModel) view.getjTableBanco().getModel();

    }

    @Override
    public void cancelar() {
        bancoActual = null;
        view.getjTextFieldCodigo().setText("");
        view.getjTextFieldNombre().setText("");
    }

    @Override
    public void grabar() {
 
        String codigo = view.getjTextFieldCodigo().getText();
        String nombre = view.getjTextFieldNombre().getText();
                
        if (codigo.isEmpty() || nombre.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (bancoActual == null) {
               service.guardar(new Banco(codigo, nombre, true));
            }else{
                bancoActual.setCodigo(codigo);
                bancoActual.setNombre(nombre);
                service.editar(bancoActual);
            }
            cancelar();
        } catch (DreamGiftsException e) {
            mostrarError(e.getMessage());
        }
    }

    @Override
    public void buscar() {
        String termino = view.getjTextFieldBuscar().getText();
        List<Banco> bancos = termino.isEmpty() ? service.buscar(): service.buscar(termino);
        tableModel.actualizar(bancos);
        view.getjTextFieldBuscar().setText("");
    }

    @Override
    public void editar() {
         int row = view.getjTableBanco().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione Banco.");
            return;
        }
        bancoActual = tableModel.getItem(row);
        view.getjTextFieldCodigo().setText(bancoActual.getCodigo());
        view.getjTextFieldNombre().setText(bancoActual.getNombre());
    }

    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        List<Integer> ids = tableModel.getSelected().stream().map(b -> b.getId()).collect(Collectors.toList());
        if (ids.isEmpty()) {
            mostrarInformacion("Seleccione banco");
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
