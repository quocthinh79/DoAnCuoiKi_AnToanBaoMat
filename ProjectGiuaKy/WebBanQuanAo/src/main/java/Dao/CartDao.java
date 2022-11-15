package Dao;

import Beans.Cart;
import Beans.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    public static boolean addToCart(int idCart, String idProduct, int quantity, String color, String size) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement prep = null;
            String sql = "";
            if (checkProduct(connection, idCart, idProduct, color, size)) {
                sql = "UPDATE CHITIETGIO SET SO_LUONG = ? WHERE MA_GIO = ? and MA_SP =? and MAUSAC = ? and KICH_THUOC = ?";
                prep = connection.prepareStatement(sql);
                prep.setInt(1, quantity);
                prep.setString(2, "" + idCart);
                prep.setString(3, idProduct);
                prep.setString(4, color);
                prep.setString(5, size);
                prep.executeUpdate();
                prep.close();
                return true;
            } else {
                sql = "INSERT INTO CHITIETGIO(MA_GIO, MA_SP,KICH_THUOC, MAUSAC, SO_LUONG) VALUES(?, ?, ?, ?, ?);";
                prep = connection.prepareStatement(sql);
                prep.setInt(1, idCart);
                prep.setString(2, idProduct);
                prep.setString(3, size);
                prep.setString(4, color);
                prep.setInt(5, quantity);
                prep.executeUpdate();
                prep.close();
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean checkProduct(Connection connection, int idCart, String idProduct, String color, String size) {
        try {
            String sql = "Select MA_GIO from CHITIETGIO where MA_SP = ? and MA_GIO = ? and MAUSAC = ? and KICH_THUOC = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, idProduct);
            prep.setInt(2, idCart);
            prep.setString(3, color);
            prep.setString(4, size);
            ResultSet resultSet = prep.executeQuery();
            int maGio = 0;
            while (resultSet.next()) {
                maGio = resultSet.getInt("MA_GIO");
            }
            prep.close();
            resultSet.close();
            return maGio != 0 ? true : false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static List<CartItem> getCartItems(int idCart) {
        String sql = "call getAllItemCart(?)";
        Connection connection = Connect.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCart);
            resultSet = statement.executeQuery();
            List<CartItem> cartItems = new ArrayList<>();
            while (resultSet.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setThumbnail(resultSet.getString("HINH_NHO"));
                cartItem.setSize(resultSet.getString("KICH_THUOC"));
                cartItem.setColor(resultSet.getString("MAUSAC"));
                cartItem.setQuantity(resultSet.getInt("SO_LUONG"));
                cartItem.setIdProduct(resultSet.getString("MA_SP"));
                cartItem.setNameProduct(resultSet.getString("TEN_SP"));
                cartItem.setPrice(resultSet.getDouble("GIA"));
                cartItems.add(cartItem);
            }
            return cartItems;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean deleteItem(int cardId, String idProduct, String size, String color) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String sql = "DELETE FROM CHITIETGIO WHERE MA_GIO = ? AND MA_SP = ? AND KICH_THUOC = ? AND MAUSAC = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, cardId);
            prep.setString(2, idProduct);
            prep.setString(3, size);
            prep.setString(4, color);
            int resultSet = prep.executeUpdate();
            if(resultSet == -1) {
                return false;
            }
            prep.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
    public static int countCartItem(int cardID) {
        int result = 0;
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "SELECT COUNT(MA_SP) FROM CHITIETGIO WHERE MA_GIO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cardID);
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
