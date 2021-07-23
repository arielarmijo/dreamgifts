/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.modelo.Controller;

/**
 *
 * @author Usuario
 */
public class EVController implements Controller<EstadoVentaView>{
    private BancoView view;
      private Banco bancoActual = null;
      private BancoTableModel tableModel;
      private final BancoService service = BancoService.getInstance();
    
    
    
}
