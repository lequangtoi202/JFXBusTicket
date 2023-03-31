/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.TuyenXe;
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
public class TuyenXeService {
    public List<TuyenXe> getAllTuyenXe() throws SQLException {
        List<TuyenXe> listTuyenXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tuyen_xe";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listTuyenXe.add(new TuyenXe(rs.getInt("Ma_Tuyen_Xe"),
                        rs.getString("Ten_Tuyen_Xe"), 
                        rs.getDouble("BangGia"), 
                        rs.getInt("Ma_ben_di"), 
                        rs.getInt("Ma_ben_den"))
                );
            }
        }
        
        return listTuyenXe;
    }
    public TuyenXe getTuyenXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM tuyen_xe WHERE Ma_Tuyen_Xe = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new TuyenXe(rs.getInt("Ma_Tuyen_Xe"),
                        rs.getString("Ten_Tuyen_Xe"), 
                        rs.getDouble("BangGia"), 
                        rs.getInt("Ma_ben_di"), 
                        rs.getInt("Ma_ben_den")
                        );
            }
        }
        return null;
    }
    public boolean addTuyenXe(TuyenXe tuyenXe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO tuyen_xe(Ten_Tuyen_Xe, BangGia, Ma_ben_di, Ma_ben_den) VALUES(?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, tuyenXe.getTenTuyen());
           stm.setDouble(2, tuyenXe.getBangGia());
           stm.setInt(3, tuyenXe.getMaBenDi());
           stm.setInt(4, tuyenXe.getMaBenDen());
           
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    public boolean deleteTuyenXe(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM tuyen_xe WHERE Ma_Tuyen_Xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
}
