package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterDao {
    public static boolean addRegister(String lastName, String firstName, String phoneNumber, String email, String
            nameAccount, String password, String token) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL themDangKy(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, nameAccount);
            preparedStatement.setString(7, password);
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
            return resultSet != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getRegisterID(String userName, String token) {
        List<Integer> ids = new ArrayList<>();
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "SELECT MA_DK FROM DANGKY WHERE TEN_TK = ? AND MA_XAC_THUC = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int registerID = resultSet.getInt(1);
                ids.add(registerID);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids.isEmpty() ? null : ids.get(0);
    }
}
