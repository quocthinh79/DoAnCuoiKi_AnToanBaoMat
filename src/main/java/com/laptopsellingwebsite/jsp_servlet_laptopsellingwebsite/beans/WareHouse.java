package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class WareHouse {
    private String maLaptop;
    private int slNhap;
    private int slXuat;
    private int slTon;

    public WareHouse(String maLaptop, int slNhap, int slXuat, int slTon) {
        this.maLaptop = maLaptop;
        this.slNhap = slNhap;
        this.slXuat = slXuat;
        this.slTon = slTon;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getSlXuat() {
        return slXuat;
    }

    public void setSlXuat(int slXuat) {
        this.slXuat = slXuat;
    }

    public int getSlTon() {
        return slTon;
    }

    public void setSlTon(int slTon) {
        this.slTon = slTon;
    }
}
