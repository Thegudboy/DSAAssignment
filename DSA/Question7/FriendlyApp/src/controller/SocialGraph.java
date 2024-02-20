/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author subad
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import database.MyConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialGraph {

    private Map<Integer, TreeNode> userNodes;

    public SocialGraph() {
        this.userNodes = new HashMap<>();
    }

  
    

    public TreeNode getUserNode(int userId) {
        return userNodes.get(userId);
    }

    // Function to establish a connection between two users
    public void establishConnection(String folwr_username, String folng_username) {
        try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO connection (follower_username, following_username) VALUES (?, ?)")) {

            pst.setString(1, folwr_username);
            pst.setString(2, folng_username);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Connection established between users: " + folwr_username + " and " + folng_username);
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void detachConnection(String folwr_username, String folng_username) {
    try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement(
            "DELETE FROM connection WHERE follower_username = ? AND following_username = ?")) {

        pst.setString(1, folwr_username);
        pst.setString(2, folng_username);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Connection detached between users: " + folwr_username + " and " + folng_username);
        } else {
            System.out.println("No connection found to detach.");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    public List<Posts> getPostsFromFollowingUsers() {
        List<Posts> posts = new ArrayList<>();

        try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement("SELECT post.id, post.username, post.discription, post.likeCount FROM post "
                + "JOIN connection ON post.username = connection.following_username "
                + "WHERE connection.follower_username = (SELECT username FROM currentuser LIMIT 1)")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int postId = rs.getInt("id");
                    String uName = rs.getString("username");
                    String description = rs.getString("discription");
                    int likes = rs.getInt("likeCount");

                    Posts post = new Posts(postId, uName, description, likes);
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return posts;
    }

    public List<Posts> getAllPosts() {
        List<Posts> posts = new ArrayList<>();

        try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM post"); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int postId = rs.getInt("id");
                String uName = rs.getString("username");

                String description = rs.getString("discription");
                int likes = rs.getInt("likeCount");

                Posts post = new Posts(postId, uName, description, likes);
                posts.add(post);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return posts;
    }

    // Method to fetch the current user's ID from the currentuser table
    private int getCurrentUserId() {
        int currentUserId = -1;  // Default value if not found

        String query = "SELECT id FROM currentuser";
        try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                currentUserId = rs.getInt("id");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return currentUserId;
    }

    // Other methods for recommendation algorithms, user interactions, etc., can be added here
}
