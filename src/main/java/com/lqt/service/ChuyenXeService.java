/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.ChuyenXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOI
 */
public class ChuyenXeService {
    public List<ChuyenXe> getAllChuyenXe() throws SQLException {
        List<ChuyenXe> listChuyenXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM chuyen_xe";
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                LocalTime localTime = rs.getTime("Thoi_gian_di").toLocalTime();
                listChuyenXe.add(new ChuyenXe(rs.getInt("Ma_Chuyen_Xe"), 
                        rs.getString("Ten_Chuyen_Xe"),
                        LocalDateTime.of(LocalDate.now(), localTime),
                        rs.getInt("Ma_Tuyen_Xe"),
                        rs.getInt("Ma_Tai_Xe")));
            }
        }
        
        return listChuyenXe;
    }
    // chua test co chuyen nao trung hay khong
    public List<ChuyenXe> getChuyenXeByBenDiAndBenDen(String benDi, String benDen) throws SQLException {
        List<ChuyenXe> listChuyenXe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT c.Ma_Chuyen_Xe, c.Ma_tai_xe, c.Ma_Tuyen_Xe, c.Ten_Chuyen_Xe, c.Thoi_gian_di\n" +
                            "FROM chuyen_xe AS c, tuyen_xe AS t\n" +
                            "WHERE c.Ma_Tuyen_Xe = t.Ma_Tuyen_Xe\n" +
                            "  AND t.Ma_ben_di = (SELECT b.Ma_Ben\n" +
                            "                     FROM ben_xe AS b\n" +
                            "                     WHERE b.Ten_Ben_Xe LIKE '%" + benDi + "%')\n" +
                            "  AND t.Ma_ben_den = (SELECT b.Ma_Ben\n" +
                            "                      FROM ben_xe AS b\n" +
                            "                      WHERE b.Ten_Ben_Xe LIKE '%" + benDen + "%')";
            
            Statement stm = conn.prepareCall(sql);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                LocalTime localTime = rs.getTime("Thoi_gian_di").toLocalTime();
                listChuyenXe.add(new ChuyenXe(rs.getInt("Ma_Chuyen_Xe"), 
                        rs.getString("Ten_Chuyen_Xe"),
                        LocalDateTime.of(LocalDate.now(), localTime),
                        rs.getInt("Ma_Tuyen_Xe"),
                        rs.getInt("Ma_Tai_Xe")));
            }
        }
        
        return listChuyenXe;
    }
    public ChuyenXe getChuyenXeById(int id) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            String sql = "SELECT * FROM chuyen_xe WHERE Ma_Chuyen_Xe = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LocalTime localTime = rs.getTime("Thoi_gian_di").toLocalTime();
                return new ChuyenXe(rs.getInt("Ma_Chuyen_Xe"), 
                        rs.getString("Ten_Chuyen_Xe"),
                        LocalDateTime.of(LocalDate.now(), localTime),
                        rs.getInt("Ma_Tuyen_Xe"),
                        rs.getInt("Ma_Tai_Xe")
                );
            }
        }
        return null;
    }
    public boolean addChuyenXe(ChuyenXe chuyenXe) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
           String sql = "INSERT INTO chuyen_xe(Ten_Chuyen_Xe, Thoi_gian_di, Ma_Tuyen_Xe, Ma_Tai_Xe) VALUES(?, ?, ?, ?)";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, chuyenXe.getTenChuyen());
           stm.setTime(2, Time.valueOf(chuyenXe.getThoiGianDi().toLocalTime()));
           stm.setInt(3, chuyenXe.getMaTuyenXe());
           stm.setInt(4, chuyenXe.getMaTaiXe());
           int r = stm.executeUpdate();
           conn.commit();
           
           return r > 0;
        }
    }
    
    public boolean deleteChuyenXe(int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "DELETE FROM chuyen_xe WHERE Ma_Chuyen_Xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setInt(1, id);
           
           return stm.executeUpdate() > 0;
        }
    }
    
    public boolean updateChuyenXe(ChuyenXe chuyenXe, int id) throws SQLException{
         try (Connection conn = JdbcUtils.getConn()) {
           String sql = "UPDATE chuyen_xe SET Ten_Chuyen_Xe = ?, Thoi_gian_di = ?, Ma_Tuyen_Xe = ?, Ma_tai_xe = ? WHERE Ma_Chuyen_Xe = ?";//SQL injection
           PreparedStatement stm = conn.prepareCall(sql);
           stm.setString(1, chuyenXe.getTenChuyen());
           stm.setTime(2, Time.valueOf(chuyenXe.getThoiGianDi().toLocalTime()));
           stm.setInt(3, chuyenXe.getMaTuyenXe());
           stm.setInt(4, chuyenXe.getMaTaiXe());
           stm.setInt(5, id);
           
           return stm.executeUpdate() > 0;
        }
    }
   
}
