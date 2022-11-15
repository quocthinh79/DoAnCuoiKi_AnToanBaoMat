package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.Brand;
import Beans.Size;

public class SizeDao {
    public static List<Size> getSizeList() {
        String sql = "SELECT S.MA_CT_KICH_THUOC, S.TEN_KICH_THUOC FROM CHITIETKICHTHUOC S";
        List<Size> sizeList = new ArrayList<>();
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size size = new Size();
                String idSize = resultSet.getString(1);
                String nameSize = resultSet.getString(2);
                size.setIdSize(idSize);
                size.setNameSize(nameSize);
                sizeList.add(size);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
    }
    
    public static List<Size> getColorOfProduct(String codeProduct) {
        String sql = "select c.ma_ct_kich_thuoc, c.ten_kich_thuoc from chitietkichthuoc c \r\n"
        		+ "left join kichthuoc k on c.ma_ct_kich_thuoc=k.ma_ct_kich_thuoc where k.ma_sp=?";
        List<Size> sizeList = new ArrayList<>();
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codeProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	  Size size = new Size();
                  size.setIdSize(resultSet.getString("ma_ct_kich_thuoc"));
                  size.setNameSize(resultSet.getString("ten_kich_thuoc"));
                  sizeList.add(size);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
    }
}
