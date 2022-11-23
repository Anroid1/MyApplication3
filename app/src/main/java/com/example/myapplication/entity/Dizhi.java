package com.example.myapplication.entity;

import java.io.Serializable;

public class Dizhi implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String diqu;
    private String xdizhi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiqu() {
        return diqu;
    }

    public void setDiqu(String diqu) {
        this.diqu = diqu;
    }

    public String getXdizhi() {
        return xdizhi;
    }

    public void setXdizhi(String xdizhi) {
        this.xdizhi = xdizhi;
    }
}
