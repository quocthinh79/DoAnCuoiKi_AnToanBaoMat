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
}
