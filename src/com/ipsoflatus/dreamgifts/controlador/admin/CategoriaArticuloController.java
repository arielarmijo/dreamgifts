/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.CategoriaArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.CategoriaArticulo;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CategoriaArticuloController {
    
    private CategoriaArticuloDao caDao = new CategoriaArticuloDao();
    
    public void guardar(String codigo, String nombre) {
        CategoriaArticulo ca = new CategoriaArticulo(codigo, nombre);
        caDao.save(ca);
    }

    public List<CategoriaArticulo> obtenerListadoCategorias() {
        return caDao.findAll();
    }

    public List<CategoriaArticulo> buscarPorTermino(String termino) {
        return caDao.findByTermLike(termino);
    }
    
}
