package controller;

import database.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class postController {

    public void addPost(String post) {
        try (Connection conn = MyConnection.dbConnect();
                PreparedStatement pstFetchUsername = conn.prepareStatement("SELECT username FROM currentuser");
                ResultSet rs = pstFetchUsername.executeQuery();
                PreparedStatement pstInsertPost = conn.prepareStatement("INSERT INTO post (username, discription) VALUES (?, ?)")) {

            // Fetch current user's username
            String username = "";
            if (rs.next()) {
                username = rs.getString("username");
            }

            // Insert post into the post table
            pstInsertPost.setString(1, username);
            pstInsertPost.setString(2, post);

            int rowsAffected = pstInsertPost.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data Inserted");
            } else {
                System.out.println("Failed to insert data");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public int getLikesCount(int postId) {
        int likesCount = 0;

        try (Connection conn = MyConnection.dbConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT likeCount FROM post WHERE id = ?")) {

            pst.setInt(1, postId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    likesCount = rs.getInt("likeCount");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return likesCount;
    }
    
    public void incrementLikes(int postId) {
        try (Connection conn = MyConnection.dbConnect();
             PreparedStatement pst = conn.prepareStatement("UPDATE post SET likeCount = likeCount+ 1 WHERE id = ?")) {

            pst.setInt(1, postId);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void decrementLikes(int postId) {
        try (Connection conn = MyConnection.dbConnect();
             PreparedStatement pst = conn.prepareStatement("UPDATE post SET likeCount = likeCount- 1 WHERE id = ?")) {

            pst.setInt(1, postId);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
