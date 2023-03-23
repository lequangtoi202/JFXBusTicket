/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

import java.util.Date;

/**
 *
 * @author TOI
 */
public class NhanVien {
    private int maNV;
    private String tenNV;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String dienThoai;
    private String CCCD;
    private String email;
    private int maUser;

    public NhanVien() {
    }

    public NhanVien(int maNV, String tenNV, boolean gioiTinh, Date ngaySinh, String diaChi, String dienThoai, String CCCD, String email, int maUser) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.CCCD = CCCD;
        this.email = email;
        this.maUser = maUser;
    }
    
    public NhanVien(String tenNV, boolean gioiTinh, Date ngaySinh, String diaChi, String dienThoai, String CCCD, String email, int maUser) {
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.CCCD = CCCD;
        this.email = email;
        this.maUser = maUser;
    }

    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    /**
     * @return the gioiTinh
     */
    public boolean isGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the dienThoai
     */
    public String getDienThoai() {
        return dienThoai;
    }

    /**
     * @param dienThoai the dienThoai to set
     */
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    /**
     * @return the CCCD
     */
    public String getCCCD() {
        return CCCD;
    }

    /**
     * @param CCCD the CCCD to set
     */
    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the maUser
     */
    public int getMaUser() {
        return maUser;
    }

    /**
     * @param maUser the maUser to set
     */
    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }
    
}
