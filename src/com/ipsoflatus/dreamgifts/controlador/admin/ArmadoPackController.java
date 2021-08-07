/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.controlador.Controller;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.servicio.PackService;
import com.ipsoflatus.dreamgifts.modelo.tabla.admin.ArmPackTableModel;
import com.ipsoflatus.dreamgifts.vista.compras.ArmadoPackView;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ArmadoPackController implements Controller<ArmadoPackView>{

    private final PackService packService;
    private ArmadoPackView view;
    private ArmPackTableModel tableModel;
    private Pack packActual;

    public ArmadoPackController() {
   this.packService = PackService.getInstance();
    }
    
    
@Override
    public void setView(ArmadoPackView view) {
       
        this.view = view;
        this.tableModel = (ArmPackTableModel) view.getjTable1().getModel();
    }

    @Override
    public void cancelar() {
        packActual = null;
        view.getjTextFieldNombre().setText("");
        view.getjSpinner1().setValue(0);
        view.getjSpinner2().setValue(0);
        view.getDatePicker1().clear();
        view.getDatePicker2().clear();
    }

    @Override
    public void grabar() {
      if (packActual == null){
         mostrarInformacion("Seleccione Pack");
            return; 
            }
      int stock = (int) view.getjSpinner1().getValue();
    

        int stockCritico = (int) view.getjSpinner2().getValue();
      LocalDate date = view.getDatePicker1().getDate();
        Date fechaInicio = Date.valueOf(date);
      date = view.getDatePicker2().getDate();
        Date fechaTermino = Date.valueOf(date);
      
           try {
        packActual.setStock(stock);
        packActual.setStockCritico(stockCritico);
        if(fechaInicio != null){
            packActual.setFechaInicio(fechaInicio);
        }
        if(fechaTermino != null){
            packActual.setFechaTermino(fechaTermino);
        }
        packService.editar(packActual);
       
            cancelar();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError(e.getMessage());
        }
      
      }

    @Override
    public void buscar() {
      String termino = view.getjTextFieldBuscar().getText();
        List<Pack> items = termino.isEmpty() ? packService.buscar(): packService.buscar(termino);
        tableModel.actualizar(items);
        view.getjTextFieldBuscar().setText("");
    }

    @Override
    public void editar() {
    int row = view.getjTable1().getSelectedRow();
        if (row == -1) {
            mostrarInformacion("Seleccione pack.");
            return;
        }
        packActual = tableModel.getItem(row);
        view.getjTextFieldNombre().setText(packActual.getNombre());
        view.getjSpinner1().setValue(packActual.getStock());
        view.getjSpinner2().setValue(packActual.getStockCritico());
        java.util.Date date = packActual.getFechaInicio();
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        view.getDatePicker1().setDate(localDate);
        date = packActual.getFechaTermino();
        localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        view.getDatePicker2().setDate(localDate);
    }
    @Override
    public void activarDesactivarSeleccionados(Boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seleccionarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
