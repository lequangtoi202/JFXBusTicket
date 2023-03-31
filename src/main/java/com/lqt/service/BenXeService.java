/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.BenXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author TOI
 */
public class BenXeService {
    public List<BenXe> getAllBenXe() throws SQLException {
        List<BenXe> listBenXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM ben_xe";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listBenXe.add(new BenXe(rs.getInt("Ma_Ben"), 
                        rs.getString("Ten_Ben_Xe")));
            }
        }
        
        return listBenXe;
    }
    
    public BenXe getBenXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM ben_xe WHERE Ma_Ben = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new BenXe(rs.getInt("Ma_Ben"), 
                        rs.getString("Ten_Ben_Xe"));
            }
        }
        return null;
    }
    
    public boolean addBenXe(BenXe benXe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO ben_xe(Ten_Ben_Xe) VALUES(?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, benXe.getTenBen());
           
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    
    public boolean deleteBenXe(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM ben_xe WHERE Ma_Ben = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
}
