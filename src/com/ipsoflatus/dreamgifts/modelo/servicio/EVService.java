/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.dao.DAO;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;



public class EVService extends AbstractService<EstadoVenta>{
    
    private static EVService instance;
    
    public EVService() {
        super(null);
    }
    
    public static EVService getInstance() {
        if (instance == null)
            instance = new EVService();
        return instance;
    }
    
}