/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TOI
 */
public class UserService {
    
    public User getUsernameAndPassword(String username, String password) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, username);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("Ma_User"), rs.getString("username"), rs.getString("password"), rs.getInt("role_id"));
            }
        }
        return null;
    }
    
    public boolean addUser(User user) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO user(username, password, role_id) VALUES(?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, user.getUsername());
           stm.setString(2, user.getPassword());
           stm.setInt(3, user.getRoleId());
           
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    
}
