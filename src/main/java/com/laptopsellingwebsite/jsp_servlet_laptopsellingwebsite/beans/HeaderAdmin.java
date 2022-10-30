package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class HeaderAdmin {
    private String tenHang;
    private String image;
    private int nhap;
    private int xuat;
    private int ton;

    public HeaderAdmin(String tenHang, String image, int nhap, int xuat, int ton) {
        this.tenHang = tenHang;
        this.image = image;
        this.nhap = nhap;
        this.xuat = xuat;
        this.ton = ton;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNhap() {
        return nhap;
    }

    public void setNhap(int nhap) {
        this.nhap = nhap;
    }

    public int getXuat() {
        return xuat;
    }

    public void setXuat(int xuat) {
        this.xuat = xuat;
    }

    public int getTon() {
        return ton;
    }

    public void setTon(int ton) {
        this.ton = ton;
    }
}
