/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.service;

import com.lqt.pojo.ChuyenXe;
import com.lqt.pojo.Ghe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOI
 */
public class GheService {
    public List<Ghe> getAllGheEmpty() throws SQLException{
        List<Ghe> dsGhe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = " SELECT g.Ma_ghe, g.So_ghe, g.Ma_xe\n" +
                            "FROM ghe as g \n" +
                            "WHERE NOT EXISTS \n" +
                            "(SELECT *\n" +
                            "FROM ve_xe as v\n" +
                            "WHERE v.Ma_ghe = g.Ma_ghe and v.Trang_thai = 'Booked' or v.Trang_thai='Done') ";
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dsGhe.add(new Ghe(rs.getInt("Ma_ghe"), 
                        rs.getString("So_ghe"),
                        rs.getInt("Ma_xe")));
            }
        }
        return dsGhe;
    }
    
    public List<Ghe> getAllGheEmptyByMaXe(int maXe) throws SQLException{
        List<Ghe> dsGhe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT g.Ma_ghe, g.So_ghe, g.Ma_xe\n" +
                            "FROM ghe as g \n" +
                            " WHERE g.Ma_ghe NOT IN\n" +
                            "(SELECT v.Ma_ghe\n" +
                            "FROM ve_xe as v\n" +
                            "WHERE v.Ma_ghe = g.Ma_ghe and v.Trang_thai = 'Booked' or v.Trang_thai='Done') and g.Ma_xe = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, maXe);
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dsGhe.add(new Ghe(rs.getInt("Ma_ghe"), 
                        rs.getString("So_ghe"),
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
                        rs.getInt("Ma_xe"));
            }
        }
        return null;
    }
}
