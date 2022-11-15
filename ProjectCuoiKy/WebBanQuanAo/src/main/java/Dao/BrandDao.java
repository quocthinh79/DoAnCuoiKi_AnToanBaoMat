package Dao;

import Beans.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDao {
    public static List<Brand> getBrandList() {
        String sql = "SELECT b.MA_NHAN_HIEU, b.TEN_NHAN_HIEU FROM NHANHIEU b";
        List<Brand> brandList = new ArrayList<>();
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                String idBrand = resultSet.getString(1);
                String nameBrand = resultSet.getString(2);
                brand.setIdBrand(idBrand);
                brand.setNameBrand(nameBrand);
                brand.setNumOfProducts(countBrands(idBrand));
                brandList.add(brand);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public static boolean addBrand(String brand_id, String brand_name) {
        String sql = "INSERT INTO NHANHIEU(MA_NHAN_HIEU, TEN_NHAN_HIEU) VALUES(?, ?)";
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand_id);
            preparedStatement.setString(2, brand_name);
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
            return resultSet == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBrand(String brand_id) {
        String sql = "DELETE FROM NHANHIEU WHERE MA_NHAN_HIEU = ?";
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand_id);
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBrand(String brand_id, String brand_name) {
        Connection connection = Connect.getInstance().getConnection();
        String sql = null;
        PreparedStatement prep = null;
        try {
            sql = "UPDATE NHANHIEU SET TEN_NHAN_HIEU = ? WHERE MA_NHAN_HIEU = ?";
            prep = connection.prepareStatement(sql);
            prep.setString(1, brand_name);
            prep.setString(2, brand_id);
            int res1 = prep.executeUpdate();
            prep.close();
            return res1 == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int countBrands(String idBrand) {
        Connection connection = Connect.getInstance().getConnection();
        String sql = "SELECT count(MA_NHAN_HIEU) from sanpham where MA_NHAN_HIEU = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idBrand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}


