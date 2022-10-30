package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class Cart {
    private String maGioHang;
    private int makh;
    private String ngayXuatGioHang;
    private long triGia;

    public Cart(String maGioHang, int makh, String ngayXuatGioHang, long triGia) {
        this.maGioHang = maGioHang;
        this.makh = makh;
        this.ngayXuatGioHang = ngayXuatGioHang;
        this.triGia = triGia;
    }

    public String getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getNgayXuatGioHang() {
        return ngayXuatGioHang;
    }

    public void setNgayXuatGioHang(String ngayXuatGioHang) {
        this.ngayXuatGioHang = ngayXuatGioHang;
    }

    public long getTriGia() {
        return triGia;
    }

    public void setTriGia(long triGia) {
        this.triGia = triGia;
    }
}
