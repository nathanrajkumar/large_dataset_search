package com.encode_initiative.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class UserService {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }

    public boolean userExists(String email) {
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Returns true if a record is found

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
