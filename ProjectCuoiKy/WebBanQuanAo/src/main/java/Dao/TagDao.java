package Dao;

import Beans.Brand;
import Beans.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDao {
	public static List<Tag> getTagList() {
		String sql = "SELECT c.MA_CT_THE, c.TEN_THE FROM CHITIETTHE c";
		List<Tag> brandList = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Tag tag = new Tag();
				String idTag = resultSet.getString(1);
				String nameTag = resultSet.getString(2);
				tag.setIdTag(idTag);
				tag.setNameTag(nameTag);
				tag.setNumOfProducts(countTags(idTag));
				brandList.add(tag);
			}
			return brandList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(preparedStatement, connection, resultSet);
		}
	}

	public static int countTags(String idTag) {
		Connection connection = Connect.getInstance().getConnection();
		String sql = "SELECT count(MA_CT_THE) from the where MA_CT_THE = ?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, idTag);
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

	// get tag of product
	public static List<Tag> getTagOfProduct(String codeProduct) {
		String sql = "select c.ma_ct_the, c.ten_the from chitietthe c \r\n"
				+ "left join the t on c.ma_ct_the=t.ma_ct_the where t.ma_sp=?";
		List<Tag> brandList = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		PreparedStatement preparedStatement= null;
		ResultSet resultSet =null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Tag tag = new Tag();
				tag.setIdTag(resultSet.getString(1));
				tag.setNameTag(resultSet.getString(2));
				brandList.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, preparedStatement, resultSet);
		}
		return brandList;
	}

	// method help close object in SQL
	public static void close(Object... objects) {
		Object[] listObject = objects;
		for (Object object : listObject) {
			try {
				if (object instanceof Connection && object != null) {
					((Connection) object).close();
				} else if (object instanceof PreparedStatement && object != null) {
					((PreparedStatement) object).close();
					;
				} else if (object instanceof ResultSet && object != null) {
					((ResultSet) object).close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
