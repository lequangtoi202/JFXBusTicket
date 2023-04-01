/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.Status;
import com.lqt.pojo.VeXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOI
 */
public class VeXeService {
    private static final VeXeService veXeService = new VeXeService();
    
    public List<VeXe> getAllVeXe() throws SQLException {
        List<VeXe> listVeXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM ve_xe";
            
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listVeXe.add(new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(rs.getDate("Gio_ban").toLocalDate(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe")
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
                        LocalDateTime.of(rs.getDate("Gio_ban").toLocalDate(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe")
                );
            }
        }
        return null;
    }
    
    public VeXe getVeXeBookedById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM bus_ticket.ve_xe where Ma_Ve_Xe = ? and Trang_thai = 'Booked';";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(rs.getDate("Gio_ban").toLocalDate(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe")
                );
            }
        }
        return null;
    }
    
    public boolean addVeXe(VeXe veXe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO ve_xe(Gio_ban, Trang_thai, Ma_nv, Ma_kh, Ma_chuyen_xe, Ma_ghe) VALUES(?, ?, ?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setTimestamp(1, Timestamp.valueOf(veXe.getThoiGianBan()));
           stm.setString(2, veXe.getTrangThai().toString());
           stm.setInt(3, veXe.getMaNV());
           stm.setInt(4, veXe.getMaKH());
           stm.setInt(5, veXe.getMaChuyenXe());
           stm.setInt(6, veXe.getMaGhe());
           
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
    
    public boolean updateVeXe(VeXe veXe, int id, Status status) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "UPDATE ve_xe SET Gio_ban = ?, Trang_thai = ?, Ma_nv = ?, Ma_kh = ?, Ma_chuyen_xe = ?, Ma_ghe = ? WHERE Ma_Ve_Xe = ?";
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setTimestamp(1, Timestamp.valueOf(veXe.getThoiGianBan()));
           stm.setString(2, status.toString());
           stm.setInt(3, veXe.getMaNV());
           stm.setInt(4, veXe.getMaKH());
           stm.setInt(5, veXe.getMaChuyenXe());
           stm.setInt(6, veXe.getMaGhe());
           stm.setInt(7, id);
           
           return stm.executeUpdate() > 0;
        }
    }
    
    public void kiemTraTgianQuaHan() throws SQLException{
        
        List<VeXe> listVeXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT v.*\n" +
                            "FROM bus_ticket.ve_xe as v, bus_ticket.chuyen_xe as c \n" +
                            "where v.Ma_chuyen_xe = c.Ma_Chuyen_Xe and TIMEDIFF(c.Thoi_gian_di, now()) <= '00:30:00' and v.Trang_thai = 'Booked';";
            
            Statement stm = conn.prepareCall(sql);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
             while (rs.next()) {
                listVeXe.add(new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(LocalDate.now(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe")
                ));
            }
        }
        
        if (!listVeXe.isEmpty()){
            listVeXe.stream().forEach(veXe -> {
                try {
                    veXeService.updateVeXe(veXe, veXe.getMaVeXe(), Status.Canceled);
                } catch (SQLException ex) {
                    Logger.getLogger(VeXeService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        
    }
    
    public void thuHoiVeXe() throws SQLException{
        
        List<VeXe> listVeXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT v.*\n" +
                            "FROM bus_ticket.ve_xe as v, bus_ticket.chuyen_xe as c \n" +
                            "where v.Ma_chuyen_xe = c.Ma_Chuyen_Xe and TIMEDIFF(c.Thoi_gian_di, now()) <= '00:05:00' and v.Trang_thai = 'Booked';";
            
            Statement stm = conn.prepareCall(sql);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listVeXe.add(new VeXe(rs.getInt("Ma_Ve_Xe"), 
                        LocalDateTime.of(LocalDate.now(), rs.getTime("Gio_ban").toLocalTime()),
                        Status.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_nv"),
                        rs.getInt("Ma_kh"),
                        rs.getInt("Ma_chuyen_xe"),
                        rs.getInt("Ma_ghe")
                ));
            }
             
            if (!listVeXe.isEmpty()){
                listVeXe.stream().forEach(veXe -> {
                    try {
                        veXeService.updateVeXe(veXe, veXe.getMaVeXe(), Status.Retrieved);
                    } catch (SQLException ex) {
                        Logger.getLogger(VeXeService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
        
    }
}
