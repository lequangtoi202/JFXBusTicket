/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOI
 */
public class KhachHangService {
    public KhachHang getKhachHangById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM khach_hang WHERE Ma_KH = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new KhachHang(rs.getInt("Ma_KH"), 
                        rs.getString("Ten_KH"), 
                        rs.getBoolean("GioiTinh"), 
                        rs.getDate("NgaySinh"), 
                        rs.getString("DiaChi"), 
                        rs.getString("DienThoai"),
                        rs.getString("CCCD"));
            }
        }
        return null;
    }
    
    public List<KhachHang> getAllKhachHang() throws SQLException {
        List<KhachHang> listKhachHang = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM khach_hang";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listKhachHang.add(new KhachHang(rs.getInt("Ma_KH"), 
                        rs.getString("Ten_KH"), 
                        rs.getBoolean("GioiTinh"), 
                        rs.getDate("NgaySinh"), 
                        rs.getString("DiaChi"), 
                        rs.getString("DienThoai"),
                        rs.getString("CCCD")));
            }
        }
        
        return listKhachHang;
    }
    
    public int addKhachHang(KhachHang kh) throws SQLException{
        int id = -1;
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO khach_hang(Ten_KH, NgaySinh, GioiTinh, DienThoai, DiaChi, CCCD) VALUES(?, ?, ?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, kh.getTenKH());
           stm.setDate(2, Date.valueOf(kh.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
           stm.setBoolean(3, kh.isGioiTinh());
           stm.setString(4, kh.getDienThoai());
           stm.setString(5, kh.getDiaChi());
           stm.setString(6, kh.getCCCD());
           int r = stm.executeUpdate();
           if (r == 0)
               throw new SQLException("Insert customer failed, no rows affected.");
           conn.commit();
           ResultSet rs = stm.getGeneratedKeys();
           if (rs.next()){
               id = rs.getInt(1);
           }
           return id;
        }
    }
    
    public boolean deleteKhachHang(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM khach_hang WHERE Ma_KH = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
    
    public boolean updateKhachHang(KhachHang kh) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "UPDATE khach_hang SET Ten_KH=?, NgaySinh=?, GioiTinh=?, DiaChi=?, CCCD=?, DienThoai=? WHERE Ma_KH = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, kh.getTenKH());
           stm.setDate(2, Date.valueOf(kh.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
           stm.setBoolean(3, kh.isGioiTinh());
           stm.setString(4, kh.getDiaChi());
           stm.setString(5, kh.getCCCD());
           stm.setString(6, kh.getDienThoai());
           stm.setInt(7, kh.getMaKH());
           return stm.executeUpdate() > 0;
        }
    }
}
