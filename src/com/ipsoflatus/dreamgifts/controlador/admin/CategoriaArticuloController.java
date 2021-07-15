package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.CategoriaArticuloDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.vista.admin.CategoriaArticuloView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CategoriaArticuloController implements TableModelListener {

    private final CategoriaArticuloDao caDao;
    private final List<String> categoriasSeleccionadas;
    private List<CategoriaArticulo> categorias;
    private CategoriaArticulo categoriaActual;
    private CategoriaArticuloView view;

    public CategoriaArticuloController() {
        caDao = new CategoriaArticuloDao();
        categoriasSeleccionadas = new ArrayList<>();
        categorias = caDao.findAll();
        categoriaActual = null;
    }

    public void setView(CategoriaArticuloView view) {
        this.view = view;
    }

    public void actualizarTabla() {
        if (categorias.isEmpty()) {
            //view.mostrarInformacion("No se encontraron registros.");
        } else {
            view.actualizarTabla(categorias);
        }
    }
    
    public void cancelar() {
        categoriaActual = null;
        view.setCodigo("");
        view.setNombre("");
    }

    public void grabar(String codigo, String nombre) {

        if (codigo.isEmpty() || nombre.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }

        try {
            if (categoriaActual == null) {
                caDao.save(new CategoriaArticulo(codigo, nombre));
            } else {
                categoriaActual.setCodigo(codigo);
                categoriaActual.setNombre(nombre);
                caDao.update(categoriaActual);
            }
            buscar("");
            categoriaActual = null;
            view.setCodigo("");
            view.setNombre("");
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }
        
    }
    
    public void buscar(String termino) {
        categorias = termino.isEmpty() ? caDao.findAll() : caDao.findByTermLike(termino);
        actualizarTabla();
    }

    public void editar(String codigo) {
        categoriaActual = caDao.findByCode(codigo);
        view.setCodigo(categoriaActual.getCodigo());
        view.setNombre(categoriaActual.getNombre());
    }
    
    public void activarSelecciondos() {
        activarDesactivarSeleccionados(true);
    }
    
    public void desactivarSelecciondos() {
        activarDesactivarSeleccionados(false);
    }
    
    public void activarDesactivarSeleccionados(boolean estado) {
        if (categoriasSeleccionadas.isEmpty()) {
            view.mostrarInformacion("Seleccione categorías.");
        } else {
            caDao.activateByCodes(categoriasSeleccionadas, estado);
            buscar(view.getBuscar());
            categoriasSeleccionadas.clear();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (row >= 0 && column >= 0) {
            TableModel model = (TableModel) e.getSource();
            boolean seleccionado = (boolean) model.getValueAt(row, column);
            String codigo = (String) model.getValueAt(row, 0);
            if (seleccionado) {
                categoriasSeleccionadas.add(codigo);
            } else {
                categoriasSeleccionadas.remove(codigo);
            }
            System.out.println("Categorías seleccionadas: " + categoriasSeleccionadas);
        }
    }

}
