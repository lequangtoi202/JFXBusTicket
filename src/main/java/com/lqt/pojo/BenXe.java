/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class BenXe {
    private int maBen;
    private String tenBen;

    public BenXe() {
    }

    public BenXe(int maBen, String tenBen) {
        this.maBen = maBen;
        this.tenBen = tenBen;
    }
    public BenXe(String tenBen) {
        this.tenBen = tenBen;
    }

    /**
     * @return the maBen
     */
    public int getMaBen() {
        return maBen;
    }

    /**
     * @param maBen the maBen to set
     */
    public void setMaBen(int maBen) {
        this.maBen = maBen;
    }

    /**
     * @return the tenBen
     */
    public String getTenBen() {
        return tenBen;
    }

    /**
     * @param tenBen the tenBen to set
     */
    public void setTenBen(String tenBen) {
        this.tenBen = tenBen;
    }

    @Override
    public String toString() {
        return this.tenBen; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
