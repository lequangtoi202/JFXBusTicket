/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class ThuHoiGheResponse {
    private int maXe;
    private int maChuyen;

    /**
     * @return the maXe
     */
    public int getMaXe() {
        return maXe;
    }

    /**
     * @return the maChuyen
     */
    public int getMaChuyen() {
        return maChuyen;
    }

    public ThuHoiGheResponse() {
    }

    public ThuHoiGheResponse(int maXe, int maChuyen) {
        this.maXe = maXe;
        this.maChuyen = maChuyen;
    }
    
}
