/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class Xe {
    private int maXe;
    private String tenXe;
    private String bienSoXe;
    private int soGhe;
    private int maLoaiXe;

    public Xe() {
    }

    public Xe(int maXe, String tenXe, String bienSoXe, int soGhe, int maLoaiXe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.bienSoXe = bienSoXe;
        this.soGhe = soGhe;
        this.maLoaiXe = maLoaiXe;
    }
    
    public Xe(String tenXe, String bienSoXe, int soGhe, int maLoaiXe) {
        this.tenXe = tenXe;
        this.bienSoXe = bienSoXe;
        this.soGhe = soGhe;
        this.maLoaiXe = maLoaiXe;
    }

    /**
     * @return the maXe
     */
    public int getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the tenXe
     */
    public String getTenXe() {
        return tenXe;
    }

    /**
     * @param tenXe the tenXe to set
     */
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    /**
     * @return the bienSoXe
     */
    public String getBienSoXe() {
        return bienSoXe;
    }

    /**
     * @param bienSoXe the bienSoXe to set
     */
    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    /**
     * @return the maLoaiXe
     */
    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    /**
     * @param maLoaiXe the maLoaiXe to set
     */
    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    @Override
    public String toString() {
        return this.tenXe;
    }
    
    
}
