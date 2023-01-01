package Dao;

import java.sql.*;

public class VerifyDao {
    public static String findHashCode(String userName,int orderId){
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "SELECT HASHING FROM XACTHUC WHERE TEN_TK = ? AND MA_HOA_DON = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addHashing(String userName, int orderId, String hashing) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "INSERT INTO XACTHUC(TEN_TK, MA_HOA_DON, HASHING) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setString(3, hashing);
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
            return resultSet != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
