package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class CartDAO {
    private static CartDAO instance;

    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }

    public String getCurrentCartByUserID(int userID) {
        String result = "";
        try {
            String command = "select magiohang from giohang where makh = ? order by magiohang desc ";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result = rs.getString(1);
                break;
            }
            return result;
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }

    }

    public ArrayList<String> getProductIDFromCartByUserID(int userID) {
        ArrayList<String> idProductList = new ArrayList<String>();
        String currentCart = getCurrentCartByUserID(userID);
        try {
            String querryLayMaLaptop = "select malaptop from ctgh where MAGIOHANG = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querryLayMaLaptop);
            ps.setString(1,currentCart);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                idProductList.add(rs.getString(1));
            }
            return idProductList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<CartInfo> getProductList (ArrayList<String> listProductID, String cartID) {
        ArrayList<CartInfo> result = new ArrayList<CartInfo>();
        try {
            for (String id_product: listProductID) {
                String querry = "SELECT thongtinlaptop.MALAPTOP, thongtinlaptop.TENLAPTOP, thongtinlaptop.LINKHINH1, ctgh.SOLUONG, thongtinlaptop.GIABAN " +
                        "from thongtinlaptop join ctgh on thongtinlaptop.MALAPTOP = ctgh.MALAPTOP where thongtinlaptop.MALAPTOP = ? and ctgh.MAGIOHANG = ?";
                PreparedStatement st = DBConnect.getInstance().get(querry);
                st.setString(1,id_product);
                st.setString(2,cartID);
                ResultSet resultSet = st.executeQuery();

                while(resultSet.next()) {
                    CartInfo product = new CartInfo(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3).toLowerCase(),
                            resultSet.getInt(4),
                            resultSet.getInt(5)
                    );
                    result.add(product);
                }
                resultSet.close();
                st.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public int getCost (String productID) {
        int result = 0;
        try {
            String querry = "select Giaban from thongtinlaptop where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querry);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int totalCost(int userID) {
        int result = 0;
        ArrayList<String> listProductID = getProductIDFromCartByUserID(userID);
        for(String productID: listProductID) {
            int cost = getCost(productID);
            int quantity = getProductQuantity(productID,userID);
            result+= cost*quantity;
        }
        return result;
    }

    public boolean updateCart(int userID, int totalCost) {
        boolean result = false;
        try {
            LocalDate dateNow = LocalDate.now();
            String getDateNow = "" + dateNow;
            String cartID = getCurrentCartByUserID(userID);
            String command = "update giohang set giohang.trigia = ?, giohang.ngayxuatgiohang = ? where magiohang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setInt(1,totalCost);
            ps.setString(2, getDateNow);
            ps.setString(3, cartID);
            int row = ps.executeUpdate();
            if (row > 0) result = true;
            ps.close();
            return result;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }

    public int getImportNumber(String productID) {
        int result = 0;
        try {
            String querry = "select slnhap from khohang where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querry);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getExportNumber(String productID) {
        int result = 0;
        try {
            String querry = "select slxuat from khohang where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querry);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRemainNumber(String productID) {
        int result = 0;
        try {
            String querry = "select tonkho from khohang where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querry);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateWarehouse(String productID, int numOfExportInDB, int numRemainInDB) {
        boolean result = false;
        try {

            String command = "update khohang set khohang.slxuat = ?, khohang.tonkho = ? where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setInt(1,numOfExportInDB);
            ps.setInt(2,numRemainInDB);
            ps.setString(3, productID);
            int row = ps.executeUpdate();
            if (row > 0) result = true;
            ps.close();
            return result;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }

    public int getSLNhap(String productID) {
        int result = 0;
        try {
            String querry = "select SLNHAP from khohang where malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(querry);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public String getCartID(int userID) {
        String result = "";
        try {

            String command = "select magiohang from giohang where makh = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }

    public boolean insertProductToCart(String productID, int userID, int quantity) {
        boolean result = false;
        try {
            String currentCard = getCurrentCartByUserID(userID);
            String command = "insert into ctgh values (?,?,?)";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,currentCard);
            ps.setString(2,productID);
            ps.setInt(3, quantity);
            int row = ps.executeUpdate();
            if (row > 0) result = true;
            ps.close();
            return result;


        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }


    public boolean updateProductQuantityByProductID(String productID, int userID, int quantityUpdated) {
        boolean result = false;
        try {
            String cartID = getCurrentCartByUserID(userID);
            String command = "update ctgh set soluong = ? where magiohang = ? and malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setInt(1,quantityUpdated);
            ps.setString(2,cartID);
            ps.setString(3, productID);
            int row = ps.executeUpdate();
            if (row > 0) result = true;
            return result;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }

    public boolean removeProductFromCart(String productID, int userID) {
        boolean result = false;
        try {
            String cartID = getCurrentCartByUserID(userID);
            String command = "DELETE FROM ctgh where magiohang = ? and malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            ps.setString(2, productID);
            int row = ps.executeUpdate();
            if (row > 0) result = true;
            return result;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return result;
        }
    }

    public int countCart() {
        try {
            int result = 0;
            String command = "select count(*) from giohang";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result =  rs.getInt(1);
                break;
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean insertCart(int userID) {
        boolean result = false;
        try {

            int cartQuantity = countCart();
            int insertedCartQuantity = cartQuantity + 1;
            String cartID = "HD" + insertedCartQuantity;

            String command = "insert into giohang values (?,?,'',0)";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            ps.setInt(2,userID);
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0)
                result = true;
            else result = false;

            return result;
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean isProductOnCart(String productID, int userID) {
        boolean check = false;
        try {
            ArrayList<String> result = new ArrayList<String>();
            String cartID = getCurrentCartByUserID(userID);
            String command = "select * FROM ctgh where magiohang = ? and malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            ps.setString(2, productID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(rs.getString(1));
            }
            for(String i: result) {
                if(i != null)
                    check = true;
                else check = false;
            }
            ps.close();
            rs.close();
            return check;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return false;
        }
    }

    public int getProductQuantity(String productID, int userID) {
        try {
            int result = 0;
            String cartID = getCurrentCartByUserID(userID);
            String command = "select soluong FROM ctgh where magiohang = ? and malaptop = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            ps.setString(2, productID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result = rs.getInt(1);
            }
            ps.close();
            rs.close();
            return result;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean clearCart(int userID) {
        try {
            boolean result = false;
            String cartID = getCurrentCartByUserID(userID);
            String command = "delete from ctgh where magiohang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            int affectedRow = ps.executeUpdate();
            String command2 = "delete from giohang where MAGIOHANG = ?";
            PreparedStatement ps2 = DBConnect.getInstance().get(command2);
            ps2.setString(1,cartID);
            int affectedRow2 = ps2.executeUpdate();
            if(affectedRow > 0 && affectedRow2 > 0) {
                result = true;
            }

            return result;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPuschased(String cartID) {
        try {
            boolean result = false;
            int cost = 0;
            String command = "select trigia from giohang where magiohang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(command);
            ps.setString(1,cartID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cost = rs.getInt(1);
            }
            if (cost != 0) {
                result = true;
            } else result = false;

            return result;

        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            return false;
        }
    }
}