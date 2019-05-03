/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Comment;
import com.mycompany.Service.ServiceCommentaire;


/**
 *
 * @author Asus
 */
public class AjoutComment {
    Form g;
    Comment rec;
    Database db;
    
    public AjoutComment(){
    
    g = new Form("Commentaire");
    rec = new Comment();
    Container c = new Container(BoxLayout.x());
    ServiceCommentaire sc = new ServiceCommentaire();
  
    //Label lcontent = new Label("comment content");
    TextArea content = new TextArea("",5,20); 
    Button btnAjouter = new Button ("Ajouter");
  
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rec.setCommentContent(content.getText());
                sc.ajouter(rec);          
                LIstComment all = new LIstComment();
                all.getG().show();
                         
            }
        });

        
        g.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });        
        g.getToolbar().addCommandToOverflowMenu("Mes Articles", null, (ev)->
            {
                Affichage art = new Affichage();
                art.getF().show();      
            }
        );
        
      //  c.add(lcontent);
        c.add(content);
        g.add(c);
        g.add(btnAjouter);
        g.show();
    }
    
    
    public Form getG() {
        return g;
    }

    public void setG(Form g) {
        this.g = g;
    }
   
}
