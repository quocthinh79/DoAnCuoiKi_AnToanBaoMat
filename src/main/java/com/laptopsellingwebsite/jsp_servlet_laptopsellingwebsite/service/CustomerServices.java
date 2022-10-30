package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.AdminDao;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CustomerDao;

import java.util.ArrayList;

public class CustomerServices {
    private static CustomerServices instance;

    private CustomerServices() {

    }

    public static CustomerServices getInstance() {
        if (instance == null) {
            instance = new CustomerServices();
        }
        return instance;
    }

    public ArrayList getAccountCurrentCustomer(int id) {
        return CustomerDao.getInstance().getAccountCurrentCustomer(id);
    }

    public String updateAccount(String hoTen, String username, String pass, String diaChi, String email, int dieuKien) {
        return CustomerDao.getInstance().updateAccount(hoTen, username, pass, diaChi, email, dieuKien);
    }
}
