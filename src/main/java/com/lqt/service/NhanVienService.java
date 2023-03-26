/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TOI
 */
public class NhanVienService {
    
    public NhanVien getNhanVienById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM nhan_vien WHERE Ma_NV = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new NhanVien(rs.getInt("Ma_NV"), 
                        rs.getString("Ten_NV"), 
                        rs.getBoolean("GioiTinh_NV"), 
                        rs.getDate("NgaySinh_NV"), 
                        rs.getString("DiaChi_NV"), 
                        rs.getString("CCCD_NV"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getInt("Ma_User"));
            }
        }
        return null;
    }
    
    public NhanVien getNhanVienByUserId(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM nhan_vien WHERE Ma_User = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new NhanVien(rs.getInt("Ma_NV"), 
                        rs.getString("Ten_NV"), 
                        rs.getBoolean("GioiTinh_NV"), 
                        rs.getDate("NgaySinh_NV"), 
                        rs.getString("DiaChi_NV"), 
                        rs.getString("CCCD_NV"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getInt("Ma_User"));
            }
        }
        return null;
    }
    
    public NhanVien getNhanVienByName(String name) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM nhan_vien WHERE Ten_NV like concat('%', ?, '%')";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, name);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new NhanVien(rs.getInt("Ma_NV"), 
                        rs.getString("Ten_NV"), 
                        rs.getBoolean("GioiTinh_NV"), 
                        rs.getDate("NgaySinh_NV"), 
                        rs.getString("DiaChi_NV"), 
                        rs.getString("CCCD_NV"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getInt("Ma_User"));
            }
        }
        return null;
    }
}
