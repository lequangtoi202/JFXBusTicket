/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class TuyenXe {
    private int maTuyenXe;
    private String tenTuyen;
    private double bangGia;
    private int maBenDi;
    private int maBenDen;

    public TuyenXe() {
    }

    public TuyenXe(int maTuyenXe, String tenTuyen, double bangGia, int maBenDi, int maBenDen) {
        this.maTuyenXe = maTuyenXe;
        this.tenTuyen = tenTuyen;
        this.bangGia = bangGia;
        this.maBenDi = maBenDi;
        this.maBenDen = maBenDen;
    }
    
    public TuyenXe(String tenTuyen, double bangGia, int maBenDi, int maBenDen) {
        this.tenTuyen = tenTuyen;
        this.bangGia = bangGia;
        this.maBenDi = maBenDi;
        this.maBenDen = maBenDen;
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
     * @return the tenTuyen
     */
    public String getTenTuyen() {
        return tenTuyen;
    }

    /**
     * @param tenTuyen the tenTuyen to set
     */
    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    /**
     * @return the bangGia
     */
    public double getBangGia() {
        return bangGia;
    }

    /**
     * @param bangGia the bangGia to set
     */
    public void setBangGia(double bangGia) {
        this.bangGia = bangGia;
    }

    /**
     * @return the maBenDi
     */
    public int getMaBenDi() {
        return maBenDi;
    }

    /**
     * @param maBenDi the maBenDi to set
     */
    public void setMaBenDi(int maBenDi) {
        this.maBenDi = maBenDi;
    }

    /**
     * @return the maBenDen
     */
    public int getMaBenDen() {
        return maBenDen;
    }

    /**
     * @param maBenDen the maBenDen to set
     */
    public void setMaBenDen(int maBenDen) {
        this.maBenDen = maBenDen;
    }

    @Override
    public String toString() {
        return this.tenTuyen; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
