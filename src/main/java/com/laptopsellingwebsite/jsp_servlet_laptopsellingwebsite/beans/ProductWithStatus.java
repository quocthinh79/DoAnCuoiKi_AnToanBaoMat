package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class ProductWithStatus {
    private String maLapTop;
    private String image;
    private String nameProduct;
    private int giaBan;
    private int trangThai;

    public ProductWithStatus(String maLapTop, String image, String nameProduct, int giaBan, int trangThai) {
        this.maLapTop = maLapTop;
        this.image = image;
        this.nameProduct = nameProduct;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public String getMaLapTop() {
        return maLapTop;
    }

    public String getImage() {
        return image;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }
}
