package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class CartDetail {
    private String maGioHang;
    private String maLaptop;
    private int soLuong;

    public CartDetail(String maGioHang, String maLaptop, int soLuong) {
        this.maGioHang = maGioHang;
        this.maLaptop = maLaptop;
        this.soLuong = soLuong;
    }

    public String getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        this.maGioHang = maGioHang;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
