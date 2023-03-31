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
public class VeXe {
    private int maVeXe;
    private LocalDateTime thoiGianBan;
    private Status trangThai;
    private int maNV;
    private int maKH;
    private int maChuyenXe;
    private int maGhe;

    public VeXe() {
    }

    public VeXe(int maVeXe, LocalDateTime thoiGianBan, Status trangThai, int maNV, int maKH, int maChuyenXe, int maGhe) {
        this.maVeXe = maVeXe;
        this.thoiGianBan = thoiGianBan;
        this.trangThai = trangThai;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maChuyenXe = maChuyenXe;
        this.maGhe = maGhe;
    }
    
    public VeXe(LocalDateTime thoiGianBan, Status trangThai, int maNV, int maKH, int maChuyenXe, int maGhe) {
        this.thoiGianBan = thoiGianBan;
        this.trangThai = trangThai;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maChuyenXe = maChuyenXe;
        this.maGhe = maGhe;
    }

    /**
     * @return the maVeXe
     */
    public int getMaVeXe() {
        return maVeXe;
    }

    /**
     * @param maVeXe the maVeXe to set
     */
    public void setMaVeXe(int maVeXe) {
        this.maVeXe = maVeXe;
    }

    /**
     * @return the thoiGianBan
     */
    public LocalDateTime getThoiGianBan() {
        return thoiGianBan;
    }

    /**
     * @param thoiGianBan the thoiGianBan to set
     */
    public void setThoiGianBan(LocalDateTime thoiGianBan) {
        this.thoiGianBan = thoiGianBan;
    }

    /**
     * @return the trangThai
     */
    public Status getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(Status trangThai) {
        this.trangThai = trangThai;
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
     * @return the maKH
     */
    public int getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(int maKH) {
        this.maKH = maKH;
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
     * @return the maGhe
     */
    public int getMaGhe() {
        return maGhe;
    }

    /**
     * @param maGhe the maGhe to set
     */
    public void setMaGhe(int maGhe) {
        this.maGhe = maGhe;
    }
    
}
