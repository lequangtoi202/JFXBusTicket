/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.Ghe;
import com.lqt.pojo.TrangThaiGhe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOI
 */
public class GheService {
    
    public List<Ghe> getAllGheEmptyByMaXe(int maXe) throws SQLException{
        List<Ghe> dsGhe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT g.Ma_ghe, g.So_ghe, g.Trang_thai, g.Ma_xe\n" +
                        "FROM ghe as g where g.Trang_thai = 'Empty' and g.Ma_xe = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, maXe);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dsGhe.add(new Ghe(rs.getInt("Ma_ghe"), 
                        rs.getString("So_ghe"),
                        TrangThaiGhe.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_xe")));
            }
        }
        return dsGhe;
    }
    
    public Ghe getGheById(int id) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = " SELECT *\n" +
                            "FROM ghe\n" +
                            "WHERE Ma_ghe = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Ghe(rs.getInt("Ma_ghe"), 
                        rs.getString("So_ghe"),
                        TrangThaiGhe.valueOf(rs.getString("Trang_thai")),
                        rs.getInt("Ma_xe"));
            }
        }
        return null;
    }
    
    public boolean updateTrangThaiGheByMaGhe(int maGhe, TrangThaiGhe trangThai) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = " UPDATE ghe as g SET g.Trang_thai= ? where g.Ma_ghe = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, trangThai.toString());
            stm.setInt(2, maGhe);
            // Truy van lay du lieu --> select
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean updateTrangThaiGheByMaXe(TrangThaiGhe trangThai, int maXe) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = " UPDATE ghe as g SET g.Trang_thai= ? where g.Ma_xe = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, trangThai.toString());
            stm.setInt(2, maXe);
            return stm.executeUpdate() > 0;
        }
    }
    
    public void thuHoiGhe() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            List<Integer> dsMaXe = new ArrayList<>();
            String sql = "select g.Ma_xe from chuyen_xe as c, ve_xe as v, ghe as g\n" +
                            "where c.Ma_Chuyen_Xe=v.Ma_Chuyen_Xe and g.Ma_ghe=v.Ma_ghe and TIMEDIFF(c.Thoi_gian_di, now()) <= '00:00:00'\n" +
                            "group by g.Ma_xe";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                dsMaXe.add(rs.getInt("Ma_xe"));
            }
            if (!dsMaXe.isEmpty())
            {
                for (Integer i : dsMaXe) {
                    updateTrangThaiGheByMaXe(TrangThaiGhe.Empty, i);
                }
            }
        }
    }
}
