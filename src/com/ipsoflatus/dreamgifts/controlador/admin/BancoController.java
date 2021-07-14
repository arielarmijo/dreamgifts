/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.BancoDao;
import com.ipsoflatus.dreamgifts.modelo.Banco;
import com.ipsoflatus.dreamgifts.vista.admin.BancoView;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Usuario
 */
public class BancoController implements TableModelListener {
    private BancoView view;
      private BancoDao bancoDao = new BancoDao();
      private Banco bancoActual = null;
   
    public void grabar(String nombre, String codigo) {
    if (bancoActual==null){
           Banco banco = new Banco(nombre, codigo, true);
    bancoDao.save(banco);
      
    } else {
        bancoActual.setNombre(nombre);
        bancoActual.setCodigo(codigo);
        bancoDao.update(bancoActual);
    }
    bancoActual = null;
    view.setNombre("");
    view.setCodigo("");
     actualizarTabla();
      
    }

    public void cancelar() {
    view.setNombre("");
    view.setCodigo("");
    }

    public void buscar(String termino) {
    view.actualizarTabla(bancoDao.findByTermLike(termino));
    }

    public void editar(String codigo) {
Banco banco = bancoDao.findByCode(codigo);
view.setNombre(banco.getNombre());
view.setCodigo(banco.getCodigo());
bancoActual = banco;
    }

    public void activarSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void desactivarSeleccionados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actualizarTabla() {
    view.actualizarTabla(bancoDao.findAll());
    }

    public void setView(BancoView vista) {
    view = vista;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
    System.out.println("probandooooo");
    }
    
}
