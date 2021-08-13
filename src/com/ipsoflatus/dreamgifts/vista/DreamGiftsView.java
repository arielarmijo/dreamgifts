package com.ipsoflatus.dreamgifts.vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DreamGiftsView extends JPanel {
    
    private DreamGifts root;

    public DreamGifts getRoot() {
        return root;
    }

    public void setRoot(DreamGifts root) {
        this.root = root;
    }
    
    public void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
