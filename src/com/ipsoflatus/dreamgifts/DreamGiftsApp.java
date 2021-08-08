package com.ipsoflatus.dreamgifts;

import com.ipsoflatus.dreamgifts.vista.DreamGifts;
import javax.swing.JFrame;

public class DreamGiftsApp {
    
    public static void main(String[] args) {
       //JFrame app = new Login(new LoginController());
       JFrame app = DreamGifts.getInstance();
       app.setLocationRelativeTo(null);
       app.setVisible(true);
    }
    
}
