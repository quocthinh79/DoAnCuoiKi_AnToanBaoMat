package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao {
    private static CustomerDao instance;

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }
    public ArrayList getAccountCurrentCustomer(int id) {
        ArrayList<Account> listResult = new ArrayList<>();
        try {
            String query = "select * from tk where id = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Account account = new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listResult.add(account);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateAccount(String hoTen, String username, String pass, String diaChi, String email, int dieuKien) {
        try {
            String query = "UPDATE `tk` SET `HOTEN`=?,`USERNAME`=?,`PASSWORD`=?,`DIACHI`=?,`EMAIL`=?  WHERE `ID`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, hoTen);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, diaChi);
            ps.setString(5, email);
            ps.setInt(6, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
