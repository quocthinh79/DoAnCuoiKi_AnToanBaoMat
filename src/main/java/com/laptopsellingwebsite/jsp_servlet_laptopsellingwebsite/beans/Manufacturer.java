package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class Manufacturer {
    private String tenHang;
    private String quocGia;
    private String poster;
    private String logoVuong;
    private String logoNgang;
    private String slogan;

    public Manufacturer(String tenHang, String quocGia, String poster, String logoVuong, String logoNgang, String slogan) {
        this.tenHang = tenHang;
        this.quocGia = quocGia;
        this.poster = poster.toLowerCase();
        this.logoVuong = logoVuong.toLowerCase();
        this.logoNgang = logoNgang.toLowerCase();
        this.slogan = slogan;
    }

    public Manufacturer() {
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getLogoVuong() {
        return logoVuong;
    }

    public void setLogoVuong(String logoVuong) {
        this.logoVuong = logoVuong;
    }

    public String getLogoNgang() {
        return logoNgang;
    }

    public void setLogoNgang(String logoNgang) {
        this.logoNgang = logoNgang;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
