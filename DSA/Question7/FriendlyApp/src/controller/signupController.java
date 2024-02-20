/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import database.MyConnection;

/**
 *
 * @author subad
 */
public class signupController {
    
    public void signUp(String username, String password){
     try (Connection conn = MyConnection.dbConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO user(Username, Password) VALUES (?, ?)")) {

            pst.setString(1, username);
            pst.setString(2, password);
            

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data Inserted");

            } else {
                System.out.println("Failed to insert data");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }   
    }
    
}
