package com.encode_initiative.commonusecaselargedatasetsearch.service;

import com.encode_initiative.commonusecaselargedatasetsearch.dto.UserDTO;
import com.encode_initiative.commonusecaselargedatasetsearch.model.Role;
import com.encode_initiative.commonusecaselargedatasetsearch.model.User;
import com.encode_initiative.commonusecaselargedatasetsearch.repository.SearchUserRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final SearchUserRepository userRepository;

    public UserService(SearchUserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public User saveUser(UserDTO userDTO) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        roles.add(role);
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setStatus("NEW USER");
        user.setRoleList(roles);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
