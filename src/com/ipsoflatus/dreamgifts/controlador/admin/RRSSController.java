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
    private final JLabel estado;
    private RRSSView view;
    private RedSocial redSocialActual;

    public RRSSController(JLabel estado) {
        redSocialDao = new RedSocialDao();
        redSocialActual = null;
        this.estado = estado;
    }

    public void setView(RRSSView view) {
        this.view = view;
    }

    public List<RedSocial> obtenerListadoRRSS() {
        return redSocialDao.findAll();
    }

    public void grabar(String nombre, String codigo) {
        if (nombre.isEmpty() || codigo.isEmpty()) {
            mostrarInformacion("Complete todos los campos.");
            return;
        }
        if (redSocialActual == null) {
            guardar(nombre, codigo);
        } else {
            actualizar(nombre, codigo);
        }
    }

    public void guardar(String nombre, String codigo) {
        try {
            redSocialActual = new RedSocial(nombre, codigo);
            redSocialDao.save(redSocialActual);
            mostrarInformacion("Red social guardada con éxito.");
            view.actualizarTabla(redSocialDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estado.setText("Error al guardar red social.");
        }
    }

    public void actualizar(String nombre, String codigo) {
        try {
            redSocialActual.setNombre(nombre);
            redSocialActual.setCodigo(codigo);
            redSocialDao.update(redSocialActual);
            mostrarInformacion("Red social actualizada con éxito.");
            view.actualizarTabla(redSocialDao.findAll());
            reset();
        } catch (DreamGiftsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estado.setText("Error al actualizar red social.");
        }
    }

    public void cancelar() {
        reset();
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
        estado.setText(mensaje);
    }

    public void editar(String codigo) {
        redSocialActual = redSocialDao.findByCode(codigo);
        view.actualizarCamposRegistro(redSocialActual);
        estado.setText(String.format("Editando datos de red social %s", redSocialActual.getNombre()));
    }
    
    public void activarSeleccionados(List<String> codigos, boolean estado) {
        if (codigos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione redes sociales.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            redSocialDao.activateRRSSByCodes(codigos, estado);
            view.actualizarTabla(redSocialDao.findAll());
            this.estado.setText(String.format("Redes sociales %s", estado ? "activadas" : "desactivadas"));
        }
    }

    private void reset() {
        view.limpiarCamposRegistro();
        redSocialActual = null;
    }

    private void mostrarInformacion(String mensaje) {
        estado.setText(mensaje);
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

}
