/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.Status;
import com.lqt.pojo.VeXe;
import com.lqt.pojo.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOI
 */
public class VeXeService {
    public List<VeXe> getAllVeXe() throws SQLException {
        List<VeXe> listVeXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM ve_xe";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listVeXe.add(new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(LocalDate.now(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe"),
                        rs.getInt("Ma_xe")
                ));
            }
        }
        
        return listVeXe;
    }
    public VeXe getVeXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM ve_xe WHERE Ma_Ve_Xe = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(LocalDate.now(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe"),
                        rs.getInt("Ma_xe")
                );
            }
        }
        return null;
    }
    public boolean addVeXe(VeXe veXe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO ve_xe(Gio_ban, Trang_thai, Ma_nv, Ma_kh, Ma_chuyen_xe, Ma_ghe, Ma_xe) VALUES(?, ?, ?, ?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setTime(1, Time.valueOf(veXe.getThoiGianBan().toLocalTime()));
           stm.setString(2, veXe.getTrangThai().toString());
           stm.setInt(3, veXe.getMaNV());
           stm.setInt(4, veXe.getMaKH());
           stm.setInt(5, veXe.getMaChuyenXe());
           stm.setInt(6, veXe.getMaGhe());
           stm.setInt(7, veXe.getMaXe());
           
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    public boolean deleteVeXe(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM ve_xe WHERE Ma_Ve_Xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
    
    public boolean updateVeXe(VeXe veXe, int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "UPDATE ve_xe SET Gio_ban = ?, Trang_thai = ?, Ma_nv = ?, Ma_kh = ?, Ma_chuyen_xe = ?, Ma_ghe = ?, Ma_xe = ? WHERE Ma_Ve_Xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setTime(1, Time.valueOf(veXe.getThoiGianBan().toLocalTime()));
           stm.setString(2, veXe.getTrangThai().toString());
           stm.setInt(3, veXe.getMaNV());
           stm.setInt(4, veXe.getMaKH());
           stm.setInt(5, veXe.getMaChuyenXe());
           stm.setInt(6, veXe.getMaGhe());
           stm.setInt(7, veXe.getMaXe());
           stm.setInt(8, id);
           
           return stm.executeUpdate() > 0;
        }
    }
}
