/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.Xe;
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
public class XeService {
    public List<Xe> getAllXe() throws SQLException {
        List<Xe> listXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "select * from xe";
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listXe.add(new Xe(rs.getInt("Ma_xe"), 
                        rs.getString("Ten_xe"),
                        rs.getString("BienSo_xe"),
                        rs.getInt("SoGhe_xe"),
                        rs.getInt("Ma_Loai_xe")
                ));
            }
        }
        
        return listXe;
    }
    public Xe getXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM xe WHERE Ma_xe = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Xe(rs.getInt("Ma_xe"), 
                        rs.getString("Ten_xe"),
                        rs.getString("BienSo_xe"),
                        rs.getInt("SoGhe_xe"),
                        rs.getInt("Ma_Loai_xe")
                );
            }
        }
        return null;
    }
    public boolean addXe(Xe xe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO xe(Ten_xe, BienSo_xe, SoGhe_xe, Ma_Loai_xe) VALUES(?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, xe.getTenXe());
           stm.setString(2, xe.getBienSoXe());
           stm.setInt(3, xe.getSoGhe());
           stm.setInt(4, xe.getMaLoaiXe());
           
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    public boolean deleteXe(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM xe WHERE Ma_xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
}
