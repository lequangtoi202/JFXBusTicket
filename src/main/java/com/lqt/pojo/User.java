/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lqt.pojo;

/**
 *
 * @author TOI
 */
public class User {
    private int maUser;
    private String username;
    private String password;
    private int roleId;

    public User() {
    }

    public User(int maUser, String username, String password, int roleId) {
        this.maUser = maUser;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    
    public User(String username, String password, int roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    
}
