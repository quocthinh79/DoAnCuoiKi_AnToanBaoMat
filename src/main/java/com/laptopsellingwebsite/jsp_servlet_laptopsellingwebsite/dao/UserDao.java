package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBConnect;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

//    static {
//        List<Account> accounts = new ArrayList<>();
//        Account ac1 = new Account(1, "Nguyen Van A", "userName1", "123456", "tp hcm", "1@gmail.com");
//        accounts.add(ac1);
//    }

    public static boolean checkUser(String userName) {
        String sql = "select username from tk where username = ?";
        try {
            PreparedStatement preparedStatement = DBConnect.getInstance().get(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPassMD5(String userName) {
        String result = null;
        String sql = "select password from tk where username = ?";
        try {
            PreparedStatement preparedStatement = DBConnect.getInstance().get(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Account getUser(String userName, String passWord) {
        Account result = null;
        String sql = "select id, hoten, username, password, diachi, email from tk where username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = DBConnect.getInstance().get(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String hoTen = resultSet.getString(2);
                String username = resultSet.getString(3);
                String pass = resultSet.getString(4);
                String diaChi = resultSet.getString(5);
                String email = resultSet.getString(6);
                result = new Account(id, hoTen, username, pass, diaChi, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public boolean checkEmail(String email) {
        String sql = "select username from tk where email = ?";
        try {
            PreparedStatement preparedStatement = DBConnect.getInstance().get(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int registerCustomer(String hoTen, String username, String pass, String diaChi, String email) {
        try {
            String queryTemp = "SELECT id FROM `tk` ORDER BY ID DESC limit 1";
            PreparedStatement ps2 = DBConnect.getInstance().get(queryTemp);
            ResultSet resultSet = ps2.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            String query = "INSERT INTO `tk` VALUES (?, ?, ?, ?, ?, ?, NULL)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, id + 1);
            ps.setString(2, hoTen);
            ps.setString(3, username);
            ps.setString(4, pass);
            ps.setString(5, diaChi);
            ps.setString(6, email);
            int result = ps.executeUpdate();

            String query2 = "INSERT INTO `phanquyen` VALUES (?, ?)";
            PreparedStatement ps3 = DBConnect.getInstance().get(query2);
            ps3.setInt(1, id + 1);
            ps3.setString(2, "CUSTOMER");
            ps3.executeUpdate();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkPermission(String username) {
        ArrayList<Account> listResult = new ArrayList<>();
        try {
            String query = "SELECT tk.ID, tk.HOTEN, tk.USERNAME, tk.PASSWORD, tk.DIACHI, tk.EMAIL  from tk join phanquyen pq on tk.ID = pq.ID WHERE pq.QUYEN = 'ADMIN' AND tk.USERNAME = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, username);
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
            if (!listResult.isEmpty())
                return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList getAccount(String email) {
        ArrayList<Account> listResult = new ArrayList<>();
        try {
            String query = "SELECT * FROM tk WHERE tk.EMAIL = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, email);
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
//    public ArrayList getInfoCustomer(String maKH) {
//        ArrayList<Customer> listResult = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM khachhang WHERE khachhang.maKH= ?";
//            PreparedStatement ps = DBConnect.getInstance().get(query);
//            ps.setString(1, maKH);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                Customer customer = new Customer(resultSet.getString(5),
//                        resultSet.getString(6),
//                        resultSet.getString(7),
//                        resultSet.getString(8));
//
//                listResult.add(customer);
//            }
//            return listResult;
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void updateOTP(String email, int otp) {
        try {
            String query = "UPDATE tk SET OTP = ? WHERE tk.EMAIL = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, otp);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList checkOTP(String email, int otp) {
        ArrayList<Account> listResult = new ArrayList<>();
        try {
            String query = "SELECT * FROM tk WHERE tk.EMAIL = ? AND tk.OTP = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, email);
            ps.setInt(2, otp);
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

    public void updatePass(String email, String newPass) {
        try {
            String query = "UPDATE tk SET PASSWORD = ? WHERE tk.EMAIL = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, newPass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
