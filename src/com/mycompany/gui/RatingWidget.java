/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.io.Preferences;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Asus
 */
public class RatingWidget {
    private static RatingWidget instance;
    private boolean running;

    private int timeForPrompt;

    private String appstoreUrl;
    private String supportEmail;

    RatingWidget() {
    }

    private void init(String appstoreUrl, String supportEmail) {
        this.appstoreUrl = appstoreUrl;
        this.supportEmail = supportEmail;
        running = true;
        Thread t = Display.getInstance().startThread(() -> checkTimerThread(), "Review thread");
        t.start();
    }

    void checkTimerThread() {
        while(running) {
            long lastTime = System.currentTimeMillis();
            int timeEllapsedInApp = Preferences.get("timeElapsedInApp", 0);
            Util.wait(this, timeForPrompt - timeEllapsedInApp);
            long total = System.currentTimeMillis() - lastTime;
            if(total + timeEllapsedInApp < timeForPrompt) {
                Preferences.set("timeElapsedInApp", (int)(total + timeEllapsedInApp));
            } else {
                Display.getInstance().callSerially(() -> showReviewWidget());
                running = false;
                instance  = null;
                return;
            }
        }
    }

    void showReviewWidget() {
        // block this from happening twice
        Preferences.set("alreadyRated", true);
        InteractionDialog id = new InteractionDialog("Please Rate "  + Display.getInstance().getProperty("AppName", "The App"));
        int height = id.getPreferredH();
        Form f = Display.getInstance().getCurrent();
        id.setLayout(new BorderLayout());
        Slider rate = createStarRankSlider();
        Button ok = new Button("OK");
        Button no = new Button("No Thanks");
        id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(rate)).
                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
        id.show(f.getHeight()  - height - f.getTitleArea().getHeight(), 0, 0, 0);
        no.addActionListener(e -> id.dispose());
        ok.addActionListener(e -> {
            id.dispose();
            if(rate.getProgress() >= 9) {
                if(Dialog.show("Rate On Store", "Would you mind rating us in the appstore?", "Go To Store", "Dismiss")) {
                    Display.getInstance().execute(appstoreUrl);
                }
            } else {
                if(Dialog.show("Tell Us Why?", "Would you mind writing us a short message explaining how we can improve?", "Write", "Dismiss")) {
                    Message m = new Message("Heres how you can improve  " + Display.getInstance().getProperty("AppName", "the app"));
                    Display.getInstance().sendMessage(new String[] {supportEmail}, "Improvement suggestions for " + Display.getInstance().getProperty("AppName", "your app"), m);
                }
            }
        });
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    /**
     * Binds the rating widget to the UI if the app wasn't rated yet
     *
     * @param time time in milliseconds for the widget to appear
     * @param appstoreUrl the app URL in the store
     * @param supportEmail support email address if the rating is low
     */
    public static void bindRatingListener(int time, String appstoreUrl, String supportEmail) {
        if(Preferences.get("alreadyRated", false)) {
            return;
        }
        instance = new RatingWidget();
        instance.timeForPrompt = time;
        instance.init(appstoreUrl, supportEmail);
    }

    /**
     * This should be invoked by the stop() method as we don't want rating countdown to proceed when the app isn't
     * running
     */
    public static void suspendRating() {
        if(instance != null) {
            synchronized(instance) {
                instance.notify();
            }
            instance.running  = false;
            instance = null;
        }
    }
    
    

    
}
