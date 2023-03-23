/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class LoaiXe {
    private int maLoaiXe;
    private String tenLoaiXe;

    public LoaiXe() {
    }

    public LoaiXe(int maLoaiXe, String tenLoaiXe) {
        this.maLoaiXe = maLoaiXe;
        this.tenLoaiXe = tenLoaiXe;
    }
    
    public LoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
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

    /**
     * @return the tenLoaiXe
     */
    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    /**
     * @param tenLoaiXe the tenLoaiXe to set
     */
    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }
    
    
}
