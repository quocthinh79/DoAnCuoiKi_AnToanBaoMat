package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maLapTop;
    private String tenLaptop;
    private String hangSX;
    private int giaBan;
    private String series;
    private String mau;
    private String cpu;
    private String vga;
    private String ram;
    private String kichThuocManHinh;
    private String oCung;
    private String banPhim;
    private String pin;
    private String khoiLuong;
    private String linkHinh1;
    private String linkHinh2;
    private String linkHinh3;
    private String linkHinh4;
    private String linkHinh5;
    public Product(){

    }

    @Override
    public String toString() {
        return "Product{" +
                "maLapTop='" + maLapTop + '\'' +
                ", tenLaptop='" + tenLaptop + '\'' +
                ", hangSX='" + hangSX + '\'' +
                ", giaBan=" + giaBan +
                ", series='" + series + '\'' +
                ", mau='" + mau + '\'' +
                ", cpu='" + cpu + '\'' +
                ", vga='" + vga + '\'' +
                ", ram='" + ram + '\'' +
                ", kichThuocManHinh='" + kichThuocManHinh + '\'' +
                ", oCung='" + oCung + '\'' +
                ", banPhim='" + banPhim + '\'' +
                ", pin='" + pin + '\'' +
                ", khoiLuong='" + khoiLuong + '\'' +
                ", linkHinh1='" + linkHinh1 + '\'' +
                ", linkHinh2='" + linkHinh2 + '\'' +
                ", linkHinh3='" + linkHinh3 + '\'' +
                ", linkHinh4='" + linkHinh4 + '\'' +
                ", linkHinh5='" + linkHinh5 + '\'' +
                '}';
    }

    public Product(String maLapTop, String tenLaptop, String hangSX, int giaBan, String series, String mau, String cpu, String vga, String ram, String kichThuocManHinh, String oCung, String banPhim, String pin, String khoiLuong, String linkHinh1, String linkHinh2, String linkHinh3, String linkHinh4, String linkHinh5) {
        this.maLapTop = maLapTop;
        this.tenLaptop = tenLaptop;
        this.hangSX = hangSX;
        this.giaBan = giaBan;
        this.series = series;
        this.mau = mau;
        this.cpu = cpu;
        this.vga = vga;
        this.ram = ram;
        this.kichThuocManHinh = kichThuocManHinh;
        this.oCung = oCung;
        this.banPhim = banPhim;
        this.pin = pin;
        this.khoiLuong = khoiLuong;
        this.linkHinh1 = linkHinh1.toLowerCase();
        this.linkHinh2 = linkHinh2.toLowerCase();
        this.linkHinh3 = linkHinh3.toLowerCase();
        this.linkHinh4 = linkHinh4.toLowerCase();
        this.linkHinh5 = linkHinh5.toLowerCase();
    }

    public String getMaLapTop() {
        return maLapTop;
    }

    public void setMaLapTop(String maLapTop) {
        this.maLapTop = maLapTop;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getKichThuocManHinh() {
        return kichThuocManHinh;
    }

    public void setKichThuocManHinh(String kichThuocManHinh) {
        this.kichThuocManHinh = kichThuocManHinh;
    }

    public String getoCung() {
        return oCung;
    }

    public void setoCung(String oCung) {
        this.oCung = oCung;
    }

    public String getBanPhim() {
        return banPhim;
    }

    public void setBanPhim(String banPhim) {
        this.banPhim = banPhim;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getLinkHinh1() {
        return linkHinh1;
    }

    public void setLinkHinh1(String linkHinh1) {
        this.linkHinh1 = linkHinh1;
    }

    public String getLinkHinh2() {
        return linkHinh2;
    }

    public void setLinkHinh2(String linkHinh2) {
        this.linkHinh2 = linkHinh2;
    }

    public String getLinkHinh3() {
        return linkHinh3;
    }

    public void setLinkHinh3(String linkHinh3) {
        this.linkHinh3 = linkHinh3;
    }

    public String getLinkHinh4() {
        return linkHinh4;
    }

    public void setLinkHinh4(String linkHinh4) {
        this.linkHinh4 = linkHinh4;
    }

    public String getLinkHinh5() {
        return linkHinh5;
    }

    public void setLinkHinh5(String linkHinh5) {
        this.linkHinh5 = linkHinh5;
    }
}
