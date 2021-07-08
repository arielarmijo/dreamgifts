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

    public void guardarRedSocial(String nombre, String codigo) {
        if (nombre.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            boolean esGuardar = (redSocialActual == null);
            if (esGuardar) {
                redSocialActual = new RedSocial(nombre, codigo);
                redSocialDao.save(redSocialActual);
            } else {
                redSocialActual.setNombre(nombre);
                redSocialActual.setCodigo(codigo);
                redSocialDao.update(redSocialActual);
            }
            String mensaje = String.format("Red social %s con éxito.", esGuardar ? "guardada" : "actualizada");
            estado.setText(mensaje);
            JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            view.actualizarTabla(redSocialDao.findAll());
            view.limpiarCamposRegistro();
            redSocialActual = null;
        } catch (DreamGiftsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estado.setText("Error al guardar red social.");
        }
    }

    public void cancelarRegistro() {
        redSocialActual = null;
        view.limpiarCamposRegistro();
    }

    public void buscarPorTermino(String terminoBuscado) {
        List<RedSocial> rrss;
        String mensaje;
        if (terminoBuscado.isEmpty()) {
            rrss = redSocialDao.findAll();
            mensaje = String.format("Redes sociales registrados %d", rrss.size());
        } else {
            rrss = redSocialDao.findByTermLike(terminoBuscado);
            view.limpiarCampoBuscar();
            mensaje = rrss.size() > 0 ? String.format("Resultado de búsqueda para %s", terminoBuscado) : "No se encontraron coincidencias.";
        }
        view.actualizarTabla(rrss);
        estado.setText(mensaje);
    }

    public void buscarRedSocalPorCodigo(String codigo) {
        redSocialActual = redSocialDao.findByCode(codigo);
        view.actualizarCamposRegistro(redSocialActual);
        estado.setText(String.format("Editando datos de red social %s", redSocialActual.getNombre()));
    }
    
}
