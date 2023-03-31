/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author TOI
 */
public class ChuyenXe {
    private int maChuyenXe;
    private String tenChuyen;
    private LocalDateTime thoiGianDi;
    private int maTuyenXe;
    private int maTaiXe;
    private boolean is_updated;

    public ChuyenXe() {
    }

    public ChuyenXe(int maChuyenXe, String tenChuyen, LocalDateTime thoiGianDi, int maTuyenXe, int maTaiXe, boolean is_updated) {
        this.maChuyenXe = maChuyenXe;
        this.tenChuyen = tenChuyen;
        this.thoiGianDi = thoiGianDi;
        this.maTuyenXe = maTuyenXe;
        this.maTaiXe = maTaiXe;
        this.is_updated = is_updated;
    }
    
    public ChuyenXe(String tenChuyen, LocalDateTime thoiGianDi, int maTuyenXe, int maTaiXe, boolean is_updated) {
        this.tenChuyen = tenChuyen;
        this.thoiGianDi = thoiGianDi;
        this.maTuyenXe = maTuyenXe;
        this.maTaiXe = maTaiXe;
        this.is_updated = is_updated;
    }

    /**
     * @return the maChuyenXe
     */
    public int getMaChuyenXe() {
        return maChuyenXe;
    }

    /**
     * @param maChuyenXe the maChuyenXe to set
     */
    public void setMaChuyenXe(int maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    /**
     * @return the tenChuyen
     */
    public String getTenChuyen() {
        return tenChuyen;
    }

    /**
     * @param tenChuyen the tenChuyen to set
     */
    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    /**
     * @return the thoiGianDi
     */
    public LocalDateTime getThoiGianDi() {
        return thoiGianDi;
    }

    /**
     * @param thoiGianDi the thoiGianDi to set
     */
    public void setThoiGianDi(LocalDateTime thoiGianDi) {
        this.thoiGianDi = thoiGianDi;
    }

    /**
     * @return the maTuyenXe
     */
    public int getMaTuyenXe() {
        return maTuyenXe;
    }

    /**
     * @param maTuyenXe the maTuyenXe to set
     */
    public void setMaTuyenXe(int maTuyenXe) {
        this.maTuyenXe = maTuyenXe;
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

    @Override
    public String toString() {
        return this.tenChuyen; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * @return the is_updated
     */
    public boolean isIs_updated() {
        return is_updated;
    }

    /**
     * @param is_updated the is_updated to set
     */
    public void setIs_updated(boolean is_updated) {
        this.is_updated = is_updated;
    }
    
    
}
