/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    Button btnaff;
 
  
    public HomeForm() {
       

    f = new Form("HOME");
        
        f.getToolbar().addCommandToSideMenu("Liste Article", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Affichage f1= new Affichage();
               f1.getF().show();
            }
        });
        
        
      f.getToolbar().addCommandToSideMenu("Ajouter Article", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                AjoutArticle a = new AjoutArticle();
                a.getF().show();
            }
        });
       f.getToolbar().addCommandToSideMenu("Mail", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Mail f= new Mail();
               f.getF().show();
                       
            }
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   

}
