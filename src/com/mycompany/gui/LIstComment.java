/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Comment;
import com.mycompany.Service.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */public class LIstComment {
    
    Form g;
    SpanLabel lb;
  
    public LIstComment() {
        
        g = new Form();
        lb = new SpanLabel("");
        ServiceCommentaire sc = new ServiceCommentaire();
        ArrayList<Comment> lis = sc.getList();
        Container y = new Container(BoxLayout.y()); 
        for (Comment c : lis) {
            //System.out.println(c.getCommentContent());
            Container j = new Container(BoxLayout.x());
            Container k = new Container(BoxLayout.y());
            Container o = new Container(BoxLayout.x());
            Button btnsup = new Button("Supprimer");
            lb = new SpanLabel("");
            lb.setText(c.getCommentContent());
            j.add(lb);
            o.add(btnsup);
            k.add(j);
            k.add(o);
            y.add(k);
            btnsup.addActionListener(e -> {
                sc.delete(c.getId());
                LIstComment s = new LIstComment();
                s.getG().show();
            });
        }
        g.add(y);
        //Form hi2 = new Form(BoxLayout.y());
        Container con = new Container(BoxLayout.y());
        Container c = new Container(BoxLayout.x());
        Button btnajout = new Button("Ajouter");
        c.add(btnajout);
        con.add(c);
        btnajout.addActionListener(event -> {
            AjoutComment f5 = new AjoutComment();
            f5.getG().show();
        });
        
        g.add(con);
        g.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });   
    }
    public Form getG() { return g; }
    public void setG(Form g) { this.g = g; }
    
}
// ALE