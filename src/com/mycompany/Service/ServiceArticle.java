/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.mycompany.Entite.Comment;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceArticle {

   
     public ArrayList<Article> getList() {
        ArrayList<Article> listCommandes = new ArrayList<>();
       ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/FooBar/web/app_dev.php/cnx");
        
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                System.out.println("listener active");
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> Article = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println("roooooot:" +Article.get("root"));
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) Article.get("root");
                
                for (Map<String, Object> obj : list) {
                    Article art = new Article();
                    
                    float id = Float.parseFloat(obj.get("id").toString());
                    art.setId((int) id);
                    art.setTitle(obj.get("title").toString());
                    art.setContent(obj.get("content").toString());
                    art.setFile(obj.get("file").toString());
                    listCommandes.add(art);
                    
                }
            } catch (IOException ex) {
            } }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommandes;
    }
     
     public void ajouter(Article ta) {
                
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/connectionmobile/ajoutarticle.php?title="+ ta.getTitle()+"&content="+ta.getContent()+"&file="+ta.getFile();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> { 
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur (traja3 tab)
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     public void delete (int  id) {
                
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/connectionmobile/deletearticle.php?id="+id;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> { 
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur (traja3 tab)
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     public void editer(Article ta) {
                
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/connectionmobile/updatearticle.php?title="+ ta.getTitle()+"&content="+ta.getContent();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> { 
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur (traja3 tab)
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
}

