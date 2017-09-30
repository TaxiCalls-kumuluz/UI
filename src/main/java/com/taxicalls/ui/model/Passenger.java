/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.ui.model;

/**
 *
 * @author romulo
 */
public class Passenger {

    private Integer id;
    private String email;
    private String password;

    protected Passenger() {
    }

    public Passenger(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
