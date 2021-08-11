package com.ipsoflatus.dreamgifts;

import com.ipsoflatus.dreamgifts.vista.Login;
import javax.swing.JFrame;

public class DreamGiftsApp {
    
    public static void main(String[] args) {
       JFrame app = new Login();
       //JFrame app = new DreamGifts();
       app.setLocationRelativeTo(null);
       app.setVisible(true);
    }
    
}
