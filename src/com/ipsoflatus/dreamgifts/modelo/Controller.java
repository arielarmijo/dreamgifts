package com.ipsoflatus.dreamgifts.modelo;

import javax.swing.JOptionPane;

public interface Controller<T> {
    
    void setView(T view);
    
    void cancelar();

    void grabar();
    
    void buscar();
    
    void editar();
    
    default void activarSeleccionados() {
        activarDesactivarSeleccionados(true);
    }
    
    default void desactivarSeleccionados() {
        activarDesactivarSeleccionados(false);
    }
    
    void activarDesactivarSeleccionados(Boolean estado);

    void seleccionarTodos();
    
    default void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    default void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
