package com.ipsoflatus.dreamgifts.controlador.admin;

import com.ipsoflatus.dreamgifts.dao.RedSocialDao;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.RedSocial;
import com.ipsoflatus.dreamgifts.vista.admin.RRSSView;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RRSSController {

    private final RedSocialDao redSocialDao;
    private RRSSView view;
    private RedSocial redSocialActual;

    public RRSSController() {
        redSocialDao = new RedSocialDao();
        redSocialActual = null;
    }

    public void setView(RRSSView view) {
        this.view = view;
    }

    public List<RedSocial> obtenerListadoRRSS() {
        return redSocialDao.findAll();
    }
    
    public void cancelar() {
        view.mostrarEstado("Administracón: Gestion de Redes Sociales.");
        reset();
    }

    public void grabar(String codigo, String nombre) {
        if (nombre.isEmpty() || codigo.isEmpty()) {
            view.mostrarInformacion("Complete todos los campos.");
            return;
        }
        if (redSocialActual == null) {
            guardar(codigo, nombre);
        } else {
            actualizar(codigo, nombre);
        }
    }

    public void buscar(String terminoBuscado) {
        List<RedSocial> rrss;
        String mensaje;
        if (terminoBuscado.isEmpty()) {
            rrss = redSocialDao.findAll();
            mensaje = String.format("Redes sociales registradas %d", rrss.size());
        } else {
            rrss = redSocialDao.findByTermLike(terminoBuscado);
            view.limpiarCampoBuscar();
            mensaje = rrss.size() > 0 ? String.format("Resultado de búsqueda para %s", terminoBuscado) : "No se encontraron coincidencias.";
        }
        view.actualizarTabla(rrss);
        view.mostrarEstado(mensaje);
    }

    public void editar(String codigo) {
        redSocialActual = redSocialDao.findByCode(codigo);
        view.actualizarCamposRegistro(redSocialActual);
        view.mostrarEstado("Editando datos de red social " + redSocialActual.getNombre());
    }
    
    public void activarSeleccionados(List<String> codigos, boolean estado) {
        if (codigos.isEmpty()) {
            view.mostrarError("Seleccione redes sociales.");
        } else {
            redSocialDao.activateRRSSByCodes(codigos, estado);
            view.actualizarTabla(redSocialDao.findAll());
            view.mostrarEstado(String.format("Redes sociales %s", estado ? "activadas" : "desactivadas"));
        }
    }

    private void reset() {
        view.limpiarCamposRegistro();
        redSocialActual = null;
    }
    
    private void guardar(String codigo, String nombre) {
        try {
            redSocialActual = new RedSocial(codigo, nombre);
            redSocialDao.save(redSocialActual);
            view.mostrarEstado("Red social guardada con éxito.");
            view.actualizarTabla(redSocialDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
            view.mostrarEstado(e.getMessage());
        }
    }

    private void actualizar(String codigo, String nombre) {
        try {
            redSocialActual.setCodigo(codigo);
            redSocialActual.setNombre(nombre);
            redSocialDao.update(redSocialActual);
            view.mostrarEstado("Red social actualizada con éxito.");
            view.actualizarTabla(redSocialDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            view.mostrarError(e.getMessage());
            view.mostrarEstado(e.getMessage());
        }
    }

}
