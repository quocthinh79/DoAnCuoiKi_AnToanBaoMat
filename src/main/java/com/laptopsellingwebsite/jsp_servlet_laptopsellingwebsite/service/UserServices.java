package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.UserDao;

import java.util.List;

public class UserServices {

    public static Account getUser(String userName, String pass) {
        Account account = UserDao.getUser(userName, pass);
        return account;
    }

    public static boolean checkUser(String userName) {
        if (UserDao.checkUser(userName))
            return true;
        return false;
    }

    private static UserServices instance;

    private UserServices() {

    }

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }

    public String getPassMD5(String username) {
        return UserDao.getInstance().getPassMD5(username);
    }

    public List<Account> getAccount(String email) {
        return UserDao.getInstance().getAccount(email);
    }

    public void updateOTP(String email, int otp) {
        UserDao.getInstance().updateOTP(email, otp);
    }

    public List<Account> checkOTP(String email, int otp) {
        return UserDao.getInstance().checkOTP(email, otp);
    }

    public void updatePass(String email, String newPass) {
        UserDao.getInstance().updatePass(email, newPass);
    }

    public boolean checkPermission(String username) {
        return UserDao.getInstance().checkPermission(username);
    }

    public int registerCustomer(String hoTen, String username, String pass, String diaChi, String email) {
        return UserDao.getInstance().registerCustomer(hoTen, username, pass, diaChi, email);
    }

    public boolean checkEmail(String email) {
        return UserDao.getInstance().checkEmail(email);
    }
}
