package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class CartInfo {
    private String maLaptop;
    private String tenLaptop;
    private String linkHinh;
    private int soluong;
    private int giaban;

    public CartInfo(String maLaptop, String tenLaptop, String linkHinh, int soluong, int giaban) {
        this.maLaptop = maLaptop;
        this.tenLaptop = tenLaptop;
        this.linkHinh = linkHinh;
        this.soluong = soluong;
        this.giaban = giaban;
    }
    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }



    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
