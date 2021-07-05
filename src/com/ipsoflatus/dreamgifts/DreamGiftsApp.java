/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts;

import com.ipsoflatus.dreamgifts.vista.DreamGifts;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class DreamGiftsApp {
    
    public static void main(String[] args) {
       //JFrame app = new Login(new LoginController());
       JFrame app = new DreamGifts();
       app.setLocationRelativeTo(null);
       app.setVisible(true);
    }
    
}
