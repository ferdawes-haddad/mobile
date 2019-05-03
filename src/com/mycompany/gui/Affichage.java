/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Article;
import com.mycompany.Service.ServiceArticle;
import com.mycompany.Service.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author skander
 */
public class Affichage {
    
     Form f;
    SpanLabel lb;
    private Resources theme;
  
    public Affichage() {
        
        f = new Form();
        lb = new SpanLabel("");
        ServiceArticle sa = new ServiceArticle();
        ArrayList<Article> lis = sa.getList();
        Container y = new Container(BoxLayout.y());
        for (Article a : lis) {
            System.out.println(a.getTitle());
            Container j = new Container(BoxLayout.x());
            Container k = new Container(BoxLayout.y());
            Container o = new Container(BoxLayout.x());

            Button btncom = new Button("Commentaire");
            Button btnsup = new Button("Supprimer");

            Container con = new Container();
            lb = new SpanLabel("");
            lb.setText("Title est  :" + a.getTitle() + "\ncontent est: " + a.getContent() + "\nfile : " + a.getFile());
            j.add(lb);
            o.add(btnsup);
            o.add(btncom);

            k.add(j);
            k.add(o);
            y.add(k);
            
            
            /*btnedit.addActionListener(e->{
                sa.editer(a);
            });*/
            
            btnsup.addActionListener(e->{
                sa.delete(a.getId());
                Affichage s =new Affichage();
                s.getF().show();
            });
            
            ServiceCommentaire sc = new ServiceCommentaire();
            btncom.addActionListener(event-> {
               sc.getList();
               LIstComment acom = new LIstComment();
               acom.getG().show();
            });
           
        }
           f.add(y);
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
          
          
    }
    
   

    Affichage(Resources theme) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
