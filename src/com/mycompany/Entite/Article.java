/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;


/**
 *
 * @author Asus
 */
public class Article {
    
    private int id;
    private String title;    
    private int user_id;
    private String content;
    private static String file;
    private String picture;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getFile() { return file; }
    public static void setFile(String file) { Article.file = file; }
   

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", user_id=" + user_id + ", content=" + content + ", file=" + file + ", picture=" + picture + '}';
    }

    
    
    
}
