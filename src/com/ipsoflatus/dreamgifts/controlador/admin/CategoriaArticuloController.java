package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.CategoriaArticuloDao;
import com.ipsoflatus.dreamgifts.modelo.CategoriaArticulo;
import java.util.List;

public class CategoriaArticuloController {
    
    private final CategoriaArticuloDao caDao = new CategoriaArticuloDao();
    private CategoriaArticulo categoriaActual;
    
    public List<CategoriaArticulo> obtenerListadoCategorias() {
        return caDao.findAll();
    }
    
    public List<CategoriaArticulo> buscarPorTermino(String termino) {
        return caDao.findByTermLike(termino);
    }
    
    public void grabar(String codigo, String nombre) {
        if (categoriaActual == null) {
            caDao.save(new CategoriaArticulo(codigo, nombre));
        } else {
            categoriaActual.setCodigo(codigo);
            categoriaActual.setNombre(nombre);
            caDao.update(categoriaActual);
        }
        categoriaActual = null;
    }
    
    public CategoriaArticulo editar(String codigo) {
        categoriaActual = caDao.findByCode(codigo);
        return categoriaActual;
    }

    public void activarSeleccionados(List<String> categoriasSeleccionadas, boolean estado) {
        caDao.activateByCodes(categoriasSeleccionadas, estado);
    }

    public void setCategoriaActual(CategoriaArticulo ca) {
        categoriaActual = ca;
    }
    
}
