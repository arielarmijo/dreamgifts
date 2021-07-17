package com.ipsoflatus.dreamgifts.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public interface ComboBoxView {
    
    default void setComboBoxModel(JComboBox cb, Object[] items) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cb.getModel();
        model.removeAllElements();
        for (Object comuna : items) {
            model.addElement(comuna);
        }
    }
    
}
