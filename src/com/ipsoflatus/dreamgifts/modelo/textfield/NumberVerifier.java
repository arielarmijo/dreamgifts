package com.ipsoflatus.dreamgifts.modelo.textfield;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NumberVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        if (input instanceof JTextField) {
            String texto = ((JTextField) input).getText();
            try {
                Integer.parseInt(texto);
                return true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(input, "Ingrese un numero.");
                return false;
            }
        }
        return true;
    }

}
