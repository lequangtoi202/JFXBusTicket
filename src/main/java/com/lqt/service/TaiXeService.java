/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.TaiXe;
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
public class TaiXeService {
    public TaiXe getTaiXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM tai_xe WHERE Ma_tx = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new TaiXe(rs.getInt("Ma_tx"), 
                        rs.getString("Ten_tx"), 
                        rs.getBoolean("GioiTinh_tx"), 
                        rs.getDate("NgaySinh_tx"), 
                        rs.getString("DiaChi_tx"), 
                        rs.getString("DienThoai_tx"),
                        rs.getString("CCCD"), 
                        rs.getString("Email"));
            }
        }
        return null;
    }
    
    public List<TaiXe> getAllTaiXe() throws SQLException {
        List<TaiXe> listTaiXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tai_xe";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listTaiXe.add(new TaiXe(rs.getInt("Ma_tx"), 
                        rs.getString("Ten_tx"), 
                        rs.getBoolean("GioiTinh_tx"), 
                        rs.getDate("NgaySinh_tx"), 
                        rs.getString("DiaChi_tx"), 
                        rs.getString("DienThoai_tx"), 
                        rs.getString("CCCD"),
                        rs.getString("Email")));
            }
        }
        
        return listTaiXe;
    }
}
