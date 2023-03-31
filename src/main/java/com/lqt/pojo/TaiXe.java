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
public class TaiXe {
    private int maTaiXe;
    private String tenTaiXe;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String dienThoai;
    private String CCCD;
    private String email;

    public TaiXe() {
    }

    public TaiXe(int maTaiXe, String tenTaiXe, boolean gioiTinh, Date ngaySinh, String diaChi, String dienThoai, String CCCD, String email) {
        this.maTaiXe = maTaiXe;
        this.tenTaiXe = tenTaiXe;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.CCCD = CCCD;
        this.email = email;
    }
    
    public TaiXe(String tenTaiXe, boolean gioiTinh, Date ngaySinh, String diaChi, String dienThoai, String CCCD, String email) {
        this.tenTaiXe = tenTaiXe;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.CCCD = CCCD;
        this.email = email;
    }

    /**
     * @return the maTaiXe
     */
    public int getMaTaiXe() {
        return maTaiXe;
    }

    /**
     * @param maTaiXe the maTaiXe to set
     */
    public void setMaTaiXe(int maTaiXe) {
        this.maTaiXe = maTaiXe;
    }

    /**
     * @return the tenTaiXe
     */
    public String getTenTaiXe() {
        return tenTaiXe;
    }

    /**
     * @param tenTaiXe the tenTaiXe to set
     */
    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
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

    @Override
    public String toString() {
        return this.tenTaiXe; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
