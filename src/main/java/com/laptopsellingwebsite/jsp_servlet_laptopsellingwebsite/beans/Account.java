package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans;

public class Account {
    private int Id;
    private String fullName;
    private String userName;
    private String pass;
    private String address;
    private String email;

    public Account() {

    }

    public Account(int Id, String fullName, String userName, String pass, String address, String email) {
        this.Id = Id;
        this.fullName = fullName;
        this.userName = userName;
        this.pass = pass;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
