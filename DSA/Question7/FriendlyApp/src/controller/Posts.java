/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author subad
 */
public class Posts {
    public int id;
    public int likes;
    public String username;
    public String discription;

    // Constructor
    public Posts(int id, String username, String discription, int like) {
        this.id = id;
        this.username = username;
        this.discription = discription;
        this.likes = like;
    }
    
    

    // Getters (you may also add setters if needed)
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return discription;
    }
    
    public void incrementLikes() {
        postController pc = new postController();
        
        pc.incrementLikes(id);
    }

    // Method to retrieve the current like count
    public int getLikes() {
        postController pc = new postController();
        
        return pc.getLikesCount(id);
    }
}

