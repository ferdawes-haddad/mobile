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
public class Comment {
    
    private int id;
    private String commentContent; 
    private int freelancer_id;
    private int article_id;
    private int parentComment_id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCommentContent() { return commentContent; }
    public void setCommentContent(String commentContent) { this.commentContent = commentContent; }

    public int getFreelancer_id() { return freelancer_id; }
    public void setFreelancer_id(int freelancer_id) { this.freelancer_id = freelancer_id; }

    public int getArticle_id() { return article_id; }
    public void setArticle_id(int article_id) { this.article_id = article_id; }

   

    public int getParentComment_id() { return parentComment_id; }
    public void setParentComment_id(int parentComment_id) { this.parentComment_id = parentComment_id; }

    public Comment(int id, String commentContent, int freelancer_id, int article_id, int parentComment_id) {
        this.id = id;
        this.commentContent = commentContent;
        this.freelancer_id = freelancer_id;
        this.article_id = article_id;
        this.parentComment_id = parentComment_id;
    }

    public Comment() {
    }
     
    
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", commentContent=" + commentContent + ", freelancer_id=" + freelancer_id + ", article_id=" + article_id + ", parentComment_id=" + parentComment_id + '}';
    }

   
    
}
