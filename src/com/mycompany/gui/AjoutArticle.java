/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Article;
import com.mycompany.Service.ServiceArticle;
import java.io.IOException;


/**
 *
 * @author Asus
 */
public class AjoutArticle {
    Form f;
    Article rec;
    Database db;
    
    
    public AjoutArticle(){
    
    f = new Form("Article");
    rec = new Article();
    Container c = new Container(BoxLayout.y());
    ServiceArticle sa = new ServiceArticle();
    
    Label ltitle = new Label("Title");
    TextField title = new TextField("", "title");
    Label lcontent = new Label("content");
    TextArea content = new TextArea("",5,10);
    Button btnimg = new Button("upload image");
    Button btnAjouter = new Button ("Ajouter");
    Button btnRating = new Button ("Rating");
    
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { 
                rec.setTitle(title.getText());
                rec.setContent(content.getText());                
                sa.ajouter(rec);              
                Affichage all = new Affichage();
                all.getF().show();         
            }
        });
        btnimg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Image img = new Image();
                try {
                    img.pictureUpload(rec);
                } catch (IOException ex) {
                }
            }
        });

        btnRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RatingWidget rat = new RatingWidget();
                rat.showReviewWidget();
            }
        });
        
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
        
        
        f.getToolbar().addCommandToOverflowMenu("Mes Articles", null, (ev)->
            {
                Affichage art = new Affichage();
                art.getF().show();
                
            }
        );
        
        c.add(ltitle);
        c.add(title);
        c.add(lcontent);
        c.add(content);
        c.add(btnimg);
        f.add(c);
        f.add(btnAjouter);
        f.add(btnRating);
        
        f.show();
    }
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   
}
