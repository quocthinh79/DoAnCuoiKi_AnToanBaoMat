package Dao;

import Beans.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorDao {
	public static List<Color> getColorList() {
		String sql = "SELECT c.MA_CT_MAU, c.TEN_MAU, c.NGAY_THEM FROM CHITIETMAU c";
		List<Color> colorList = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Color color = new Color();
				String idColor = resultSet.getString(1);
				String nameColor = resultSet.getString(2);
				Timestamp createDate = resultSet.getTimestamp(3);
				color.setIdColor(idColor);
				color.setNameColor(nameColor);
				color.setCreateDate(createDate);
				color.setNumOfProducts(countColors(idColor));
				colorList.add(color);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colorList;
	}

	public static boolean addColor(String idColor, String colorName) {
		Connection connection = Connect.getInstance().getConnection();
		try {
			String query = "INSERT INTO chitietmau(MA_CT_MAU, TEN_MAU) VALUES (?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, idColor);
			preparedStatement.setString(2, colorName);
			int resultSet = preparedStatement.executeUpdate();
			preparedStatement.close();
			return resultSet != -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteColor(String idColor) {
		Connection connection = Connect.getInstance().getConnection();
		try {
			String query = "DELETE FROM CHITIETMAU WHERE MA_CT_MAU =?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, idColor);
			int resultSet = preparedStatement.executeUpdate();
			preparedStatement.close();
			return resultSet != -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editColor(String idColor, String colorName) {
		Connection connection = Connect.getInstance().getConnection();
		try {
			String query = "UPDATE CHITIETMAU SET TEN_MAU =? WHERE MA_CT_MAU =?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, colorName);
			preparedStatement.setString(2, idColor);
			int resultSet = preparedStatement.executeUpdate();
			preparedStatement.close();
			return resultSet != -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int countColors(String idColor) {
		Connection connection = Connect.getInstance().getConnection();
		String sql = "SELECT count(MA_CT_MAU) from mau where MA_CT_MAU = ?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, idColor);
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

	public static List<Color> getColorOfProduct(String codeProduct) {
		String sql = "select c.ma_ct_mau, c.ten_mau from chitietmau c \r\n"
				+ "left join mau m on c.ma_ct_mau=m.ma_ct_mau where m.ma_sp=?";
		List<Color> colorList = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Color color = new Color();
				String idColor = resultSet.getString(1);
				String nameColor = resultSet.getString(2);
				color.setIdColor(idColor);
				color.setNameColor(nameColor);
				colorList.add(color);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colorList;
	}
}
