package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.RedSocialDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.RedSocial;
import com.ipsoflatus.dreamgifts.vista.admin.RRSSView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class RRSSController implements TableModelListener {

    private final RedSocialDao redSocialDao;
    private final List<String> redesSocialesSeleccionadas;
    private List<RedSocial> rrss;
    private RRSSView view;
    private RedSocial redSocialActual;
    

    public RRSSController() {
        redSocialDao = new RedSocialDao();
        redesSocialesSeleccionadas = new ArrayList<>();
        rrss = redSocialDao.findAll();
        redSocialActual = null;
    }

    public void setView(RRSSView view) {
        this.view = view;
    }

    public void actualizarTabla() {
        if (rrss.isEmpty()) {
            view.mostrarEstado("No se encontraron registros.");
        } else {
            view.actualizarTabla(rrss);
            view.mostrarEstado(String.format("Mostrando %d registros.", rrss.size()));
        }
    }

    public void cancelar() {
        redSocialActual = null;
        view.setCodigo("");
        view.setNombre("");
        view.mostrarEstado("Administración: Redes Sociales.");
    }

    public void grabar(String codigo, String nombre) {
        
        if (nombre.isEmpty() || codigo.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        
        try {
            String estado;
            if (redSocialActual == null) {
                redSocialDao.save(new RedSocial(codigo, nombre));
                estado = "Red social guardada con éxito.";
            } else {
                redSocialActual.setCodigo(codigo);
                redSocialActual.setNombre(nombre);
                redSocialDao.update(redSocialActual);
                estado = "Red social actualizada con éxito.";
            }
            buscar("");
            redSocialActual = null;
            view.setCodigo("");
            view.setNombre("");
            view.mostrarEstado(estado);
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
        }

    }

    public void buscar(String termino) {
        rrss = termino.isEmpty() ? redSocialDao.findAll() : redSocialDao.findByTermLike(termino);
        actualizarTabla();
    }

    public void editar(String codigo) {
        redSocialActual = redSocialDao.findByCode(codigo);
        view.setNombre(redSocialActual.getNombre());
        view.setCodigo(redSocialActual.getCodigo());
        view.mostrarEstado("Editando red social " + redSocialActual.getNombre());
    }
    
    public void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }

    public void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }

    public void activarDesactivarSeleccionados(boolean estado) {
        if (redesSocialesSeleccionadas.isEmpty()) {
            view.mostrarError("Seleccione redes sociales.");
        } else {
            redSocialDao.activateByCodes(redesSocialesSeleccionadas, estado);
            buscar(view.getBuscar());
            redesSocialesSeleccionadas.clear();
            view.mostrarEstado(String.format("Redes sociales %s", estado ? "activadas" : "desactivadas"));
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
                redesSocialesSeleccionadas.add(codigo);
            } else {
                redesSocialesSeleccionadas.remove(codigo);
            }
            System.out.println("Redes sociales seleccionadas: " + redesSocialesSeleccionadas);
        }
    }

}
