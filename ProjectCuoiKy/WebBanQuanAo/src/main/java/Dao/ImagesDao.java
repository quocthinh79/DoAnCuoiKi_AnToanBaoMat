package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.Image;

public class ImagesDao {
	public static List<Image> getImagesOfProduct(String codeProduct) {
		String sql = "select h.ma_ct_mau, h.duong_dan_anh from hinhanh h where h.ma_sp=?";
		List<Image> imageList = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Image image = new Image();
//				image.setColorCode(resultSet.getString("ma_ct_mau"));
//				image.setPathImage(resultSet.getString("duong_dan_anh"));
				imageList.add(image);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageList;

	}
}
