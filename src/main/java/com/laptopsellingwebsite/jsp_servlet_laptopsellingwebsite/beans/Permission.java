package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class Permission {
    private int id;
    private String quyen;

    public Permission(int id, String quyen) {
        this.id = id;
        this.quyen = quyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
}
