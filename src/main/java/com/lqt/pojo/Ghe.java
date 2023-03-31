/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class Ghe {
    private int maGhe;
    private String soGhe;
    private TrangThaiGhe trangThai;
    private int maXe;

    public Ghe() {
    }

    public Ghe(int maGhe, String soGhe, TrangThaiGhe trangThai, int maXe) {
        this.maGhe = maGhe;
        this.soGhe = soGhe;
        this.maXe = maXe;
        this.trangThai = trangThai;
    }
    public Ghe(String soGhe, TrangThaiGhe trangThai, int maXe) {
        this.maXe = maXe;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
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

    /**
     * @return the soGhe
     */
    public String getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
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
    
    
    
     @Override
    public String toString() {
        return this.soGhe;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiGhe getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiGhe trangThai) {
        this.trangThai = trangThai;
    }
}
