/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Article;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Asus
 */
public class Image {
    
    
    public void pictureUpload(Article ta) throws IOException {
      
        MultipartRequest cr = new MultipartRequest();
        String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
        String url = "http://localhost/connectionmobile/Image.php?temp="+ filePath;
        System.out.println(ta.getFile());
        System.out.println(url);
        System.out.println(filePath);
        cr.setUrl(url);
        cr.setPost(true);
        String mime = "image/jpeg";
        cr.addData("file", filePath, mime);
        cr.setFilename("file", "MyImage.jpg");//any unique name you want
        
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        cr.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(cr);
        
        
        

       /* Toolbar.setGlobalToolbar(true);
        hi = new Form("Rounder", new BorderLayout());
        theme = UIManager.initFirstTheme("/theme");
        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
        picture = new Label("", "Container");
        hi.add(BorderLayout.CENTER, picture);
        hi.getUnselectedStyle().setBgColor(0xff0000);
        hi.getUnselectedStyle().setBgTransparency(255);
        s = UIManager.getInstance().getComponentStyle("TitleCommand");
        camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
        hi.getToolbar().addCommandToRightBar("", camera, (ev) -> {
            try {
                int width = Display.getInstance().getDisplayWidth();
                capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
                roundMask = Image.createImage(width, capturedImage.getHeight(), 0xff000000);
                gr = roundMask.getGraphics();
                gr.setColor(0xffffff);
                gr.fillArc(0, 0, width, width, 0, 360);
                mask = roundMask.createMask();
                capturedImage = capturedImage.applyMask(mask);
                picture.setIcon(capturedImage);
                hi.revalidate();
                hi.add(capturedImage);
            } catch (IOException err) {
                Log.e(err);
            }
        });*/
  
    }

}
