/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author achraf
 */
class BaseForm {  public BaseForm(Layout contentPaneLayout){
       // super(contentPaneLayout);
    }
    public BaseForm(){
        
    }
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
      //  inboxButton.addActionListener(e -> new InboxForm().show());
        //getToolbar().addComponentToSideMenu(inbox);
          //      getToolbar().addCommandToSideMenu("Events", statsImage, e -> new EventsAll());

      //  getToolbar().addCommandToSideMenu("Restaurant", statsImage, e -> new RestaurantGui().getF().show());
       // getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
       // getToolbar().addCommandToSideMenu("Voyage", trendingImage, e ->
      /*          new VoyageGui(res).show());
        getToolbar().addCommandToSideMenu("Recommendation", trendingImage, e -> {
            try {
                new RecommendationGui(res).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        getToolbar().addCommandToSideMenu("Prestations", trendingImage, e -> {new ListPrestation().showlist(new PrestationService().findall());});
        getToolbar().addCommandToSideMenu("Produits", trendingImage, e -> new HomeProduit(res).show());

        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
*/
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
    
}
