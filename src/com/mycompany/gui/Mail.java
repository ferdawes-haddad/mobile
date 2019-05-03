/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;


import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;



/**
 *
 * @author Asus
 */
public class Mail {
     Form f;
    SpanLabel lb;
  
    public Mail() {
        
        f = new Form();
        lb = new SpanLabel("");
        //f.add(lb);
      
         Container y = new Container(BoxLayout.y());
        //lb.setText(lis.toString());
        Container c= new Container(BoxLayout.y());

       Container g= new Container(BoxLayout.y());

        TextField body= new TextField("","description",150,50);

       TextField title = new TextField("","title",50,0);

            Button send = new Button("Send Mail");
         

               Container con = new Container();
               
              
         
         send.addActionListener((ActionListener) (ActionEvent evt1) -> {
         
         Message m = new Message(body.getText());
           
            m.setMimeType(Message.MIME_HTML);
           m.getAttachments().put(body.getText(), "text/plain");
          Display.getInstance().sendMessage(new String[] {"ferdawes.haddad@esprit.tn"}, title.getText(), m);
         
         });   c.add(title);
               c.add(body);
         
           //    j.add(imgv);
               
               c.add(send);
                
               
             g.add(c);
              f.add(g);
              TextField sms=new TextField();
        Button envoyer = new Button("envoyer sms");
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    NexmoClient client = new NexmoClient.Builder()
                            .apiKey("8b16ffff")
                            .apiSecret("4uEYdBmHAuhRUZSV")
                            .build();              
                    String messageText = sms.getText();
                    TextMessage message = new TextMessage("Nexmo", "+21653528263", messageText);                   
                    SmsSubmissionResponse response = null;                 
                    response = client.getSmsClient().submitMessage(message);
                    for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                        System.out.println(responseMessage);
                    }   
                    System.out.println(messageText);
                }
                catch (IOException ex) {                                 
                   
                } catch (NexmoClientException ex) {
                   
                }
                                
            }
        });
        f.add(sms);
          f.add(envoyer);
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    Mail(Resources theme) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}