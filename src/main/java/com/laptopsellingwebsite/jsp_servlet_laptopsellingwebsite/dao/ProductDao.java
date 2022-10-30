package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Manufacturer;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.ProductWithStatus;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBConnect;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class ProductDao {
    private static ProductDao instance;
    ArrayList<Manufacturer> listProducer;
    int count = 0;


    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

//    public List<Product> getAllProduct(){
//        return JDBIConnection.get().withHandle(handle -> {
//           return handle.createQuery("select * from thongtinlaptop").mapToBean(Product.class).stream().collect(Collectors.toList());
//        });
//    }

    public ArrayList getAllProduct(int limit, int page) {
        ArrayList<Product> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from thongtinlaptop LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19));
                listResult.add(product);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getAllProductWithStatus(int limit, int page) {
        ArrayList<ProductWithStatus> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                        resultSet.getString(2).toLowerCase(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listResult.add(product);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPage() {
        try {
            String query = "select count(*) as total from thongtinlaptop";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList getAllProductWithStatus() {
        ArrayList<ProductWithStatus> listResult = new ArrayList<>();
        try {
            String query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                        resultSet.getString(2).toLowerCase(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listResult.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList getAllProduct() {
        ArrayList<Product> listResult = new ArrayList<>();
        try {
            String query = "select * from thongtinlaptop";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19));
                listResult.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList getAllProduct(String temp, int limit, int page) {
        ArrayList<ProductWithStatus> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query;
            if (temp != null) {
                query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP" + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
            } else {
                query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP LIMIT " + "?" + " OFFSET " + "?";
            }
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                        resultSet.getString(2).toLowerCase(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listResult.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList getAllProducer() {
        listProducer = new ArrayList<>();
        try {
            String query = "select * from hangsx";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Manufacturer produccer = new Manufacturer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listProducer.add(produccer);
            }
            resultSet.close();
            ps.close();
            return listProducer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getTopProduct1(int num) {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            for (Manufacturer x : listProducer) {
                String query = "select * from thongtinlaptop JOIN hangsx on HANG = TENHANG WHERE HANG = ? ORDER BY thongtinlaptop.GIABAN DESC LIMIT ?";
                PreparedStatement ps = DBConnect.getInstance().get(query);
                ps.setString(1, x.getTenHang());
                ps.setInt(2, num);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10),
                            resultSet.getString(11),
                            resultSet.getString(12),
                            resultSet.getString(13),
                            resultSet.getString(14),
                            resultSet.getString(15),
                            resultSet.getString(16),
                            resultSet.getString(17),
                            resultSet.getString(18),
                            resultSet.getString(19));
                    listProduct.add(product);
                }
            }
            return listProduct;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getTopProductBestSeller(int num) {
        ArrayList<ProductWithStatus> listProductBestSeller = new ArrayList<>();
        try {
                String query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP ORDER BY kh.SLXUAT DESC LIMIT ?";
                PreparedStatement ps = DBConnect.getInstance().get(query);
                ps.setInt(1, num);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                            resultSet.getString(2).toLowerCase(),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5));
                    listProductBestSeller.add(product);
                }
            return listProductBestSeller;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProductManufacturer(String manufacturer) {
        ArrayList<Product> listProductManufacturer = new ArrayList<>();
        try {
            String query = "SELECT * FROM THONGTINLAPTOP WHERE HANG = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, manufacturer);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19));
                listProductManufacturer.add(product);
            }
//            }
            return listProductManufacturer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProductManufacturer(String manufacturer, int limit, int page) {
        ArrayList<ProductWithStatus> listProductManufacturer = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP WHERE HANG = ? LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, manufacturer);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                        resultSet.getString(2).toLowerCase(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listProductManufacturer.add(product);
            }
//            }
            return listProductManufacturer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProductManufacturer(String manufacturer, String temp, int limit, int page) {
        ArrayList<ProductWithStatus> listProductManufacturer = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query;
            if (temp != null) {
                query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP WHERE HANG = ?" + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
            } else {
                query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP WHERE HANG = ?" + " LIMIT " + "?" + " OFFSET " + "?";
            }
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, manufacturer);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProductWithStatus product = new ProductWithStatus(resultSet.getString(1),
                        resultSet.getString(2).toLowerCase(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listProductManufacturer.add(product);
            }
//            }
            return listProductManufacturer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProducerWithID(String producer) {
        ArrayList<Manufacturer> listProducer = new ArrayList<>();
        try {
            String query = "SELECT * FROM HANGSX WHERE TENHANG = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, producer);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Manufacturer produccer = new Manufacturer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listProducer.add(produccer);
            }
            resultSet.close();
            ps.close();
            return listProducer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProductWithID(String ID) {
        ArrayList<Product> listProductWithID = new ArrayList<>();
        try {
            String query = "SELECT * FROM THONGTINLAPTOP WHERE MALAPTOP = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, ID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19));
                listProductWithID.add(product);
            }
            resultSet.close();
            ps.close();
            return listProductWithID;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getProductWithProducer(String producer) {
        ArrayList<Product> listProductWithID = new ArrayList<>();
        try {
            String query = "SELECT * FROM THONGTINLAPTOP WHERE HANG = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, producer);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19));
                listProductWithID.add(product);
            }
            resultSet.close();
            ps.close();
            return listProductWithID;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getTopProducer(int num) {
        ArrayList listTopProducer = new ArrayList<>();
        try {
            String query = "select * from hangsx limit ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, num);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Manufacturer produccer = new Manufacturer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listTopProducer.add(produccer);
            }
            resultSet.close();
            ps.close();
            return listTopProducer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList sortWithPrice(int highPrice, int lowPrice) {
        ArrayList result = new ArrayList();
        try {
            String query = "select * from THONGTINLAPTOP WHERE GIABAN BETWEEN ? and ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, lowPrice);
            ps.setInt(2, highPrice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList sortPriceWithProducer(int highPrice, int lowPrice, String hangSX) {
        ArrayList result = new ArrayList();
        try {
            String query = "select * from THONGTINLAPTOP WHERE HANG = ? AND GIABAN BETWEEN ? and ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, hangSX);
            ps.setInt(2, lowPrice);
            ps.setInt(3, highPrice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Product> sortProduct(Multimap<String, String> map, String temp) {
        ArrayList<Product> result = new ArrayList();
        try {
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (!joinString.equals("") && !queryTemp.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE" + queryTemp + " AND " + " %s " + " ORDER BY GIABAN " + temp;
            } else if (joinString.equals("") && !queryTemp.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE" + queryTemp + " ORDER BY GIABAN " + temp;
            } else if (queryTemp.equals("") && !joinString.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE" + " %s " + " ORDER BY GIABAN " + temp;
            } else {
                query = "select * from THONGTINLAPTOP " + " ORDER BY GIABAN " + temp;
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            int count = 1;
            if (!joinString.equals("") && !queryTemp.equals("")) {
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
            } else if (joinString.equals("") && !queryTemp.equals("")) {
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
            }
            map.removeAll("GIABAN");
            for (String x : map.values()) {
                ps.setString(count++, x);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductWithStatus> sortProduct(Multimap<String, String> map, String temp, int limit, int page) {
        ArrayList<ProductWithStatus> result = new ArrayList();
        try {
            int offset = (page - 1) * limit;
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + queryTemp + " AND " + " %s " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + queryTemp + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + " %s " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + queryTemp + " AND " + " %s " + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + queryTemp + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE" + " %s " + " LIMIT " + "?" + " OFFSET " + "?";
                } else {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + " LIMIT " + "?" + " OFFSET " + "?";
                }
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            int count = 1;
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                } else {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                    ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                } else {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            }
            map.removeAll("GIABAN");
            for (String x : map.values()) {
                ps.setString(count++, x);
            }
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else {
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else {
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductWithStatus product = new ProductWithStatus(rs.getString(1),
                        rs.getString(2).toLowerCase(),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int sortProductTotalPage(Multimap<String, String> map) {
        try {
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (!joinString.equals("") && !queryTemp.equals("")) {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " GIABAN BETWEEN ? AND ?" + " AND " + " %s ";
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " GIABAN BETWEEN ? AND ?";
            } else {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " %s ";
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            int count = 1;
            if (!joinString.equals("") && !queryTemp.equals("")) {
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[0].toString().replace(".", "")));
                ps.setInt(count++, Integer.parseInt(map.get("GIABAN").toArray()[1].toString().replace(".", "")));
            }
            map.removeAll("GIABAN");
            for (String x : map.values()) {
                try {
                    int number = Integer.parseInt(x.replace(".", ""));
                    ps.setInt(count++, number);
                } catch (Exception e) {
                    ps.setString(count++, x);
                }
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int sortProductTotalPageByProducer(Multimap<String, String> map, String hangsx) {
        try {
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (!joinString.equals("") && !queryTemp.equals("")) {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " HANG = " + "?" + " AND " + "?" + " AND " + " %s ";
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " HANG = " + "?" + " AND " + "?";
            } else {
                query = "select count(*) as total from THONGTINLAPTOP " + "WHERE" + " HANG = " + "?" + " AND " + " %s ";
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            ps.setString(1, hangsx);
            int count = 2;
            if (!joinString.equals("") && !queryTemp.equals("")) {
                ps.setString(count++, queryTemp);
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                ps.setString(count++, queryTemp);
            }
            for (String x : map.values()) {
                try {
                    int number = Integer.parseInt(x.replace(".", ""));
                    ps.setInt(count++, number);
                } catch (Exception e) {
                    ps.setString(count++, x);
                }
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Product> sortProductWithProducer(Map<String, String> map, String hangsx) {
        ArrayList<Product> result = new ArrayList();
        try {
            String query = "";
            String joinString = map.entrySet().stream()
                    .map(e -> (!e.getKey().equalsIgnoreCase("GIABAN")) ?
                            (e.getKey() + " IN " + "(" + e.getValue() + ")") :
                            (e.getKey() + " BETWEEN " + e.getValue()))
                    .collect(joining(" AND "));
            if (!joinString.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " AND %s " + " ORDER BY GIABAN ";
            } else {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " ORDER BY GIABAN ";
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            ps.setString(1, hangsx);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Product> sortProductWithProducer(Multimap<String, String> map, String hangsx, String temp) {
        ArrayList<Product> result = new ArrayList();
        try {
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (!joinString.equals("") && !queryTemp.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " AND %s " + " ORDER BY GIABAN " + "?";
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " ORDER BY GIABAN " + "?";
            } else if (queryTemp.equals("") && !joinString.equals("")) {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " AND %s " + " ORDER BY GIABAN " + "?";
            } else {
                query = "select * from THONGTINLAPTOP " + "WHERE HANG = " + " ? " + " ORDER BY GIABAN " + "?";
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            ps.setString(1, hangsx);
            int count = 2;
            if (!joinString.equals("") && !queryTemp.equals("")) {
                ps.setString(count++, queryTemp);
            } else if (!queryTemp.equals("") && joinString.equals("")) {
                ps.setString(count++, queryTemp);
                ps.setString(count++, temp);
            } else {
                ps.setString(count++, temp);
            }
            for (String x : map.values()) {
                ps.setString(count++, x);
            }
            if (!joinString.equals("") && !queryTemp.equals("")) {
                ps.setString(count++, temp);
            } else if (queryTemp.equals("") && !joinString.equals("")) {
                ps.setString(count++, temp);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductWithStatus> sortProductWithProducer(Multimap<String, String> map, String hangsx, String temp, int limit, int page) {
        ArrayList<ProductWithStatus> result = new ArrayList();
        try {
            int offset = (page - 1) * limit;
            String queryTemp = "";
            Multimap<String, String> mapTemp = TreeMultimap.create();
            if (map.keys().contains("GIABAN")) {
                queryTemp = " GIABAN BETWEEN " + map.get("GIABAN").stream().map(e -> "?")
                        .collect(Collectors.joining(" AND "));
                for (String x : map.get("GIABAN")) {
                    mapTemp.put("GIABAN", x);
                }
                map.removeAll("GIABAN");
            }
            String joinString = map.asMap()
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue()
                            .stream()
                            .map(v -> (!e.getKey().equalsIgnoreCase("GIABAN")) ? (e.getKey() + " = " + "?") : (e.getKey() + " IN " + "?"))
                            .collect(Collectors.joining(" OR ", "(", ")")))
                    .collect(Collectors.joining(" AND "));

            String query = "";
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " AND %s " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (!queryTemp.equals("") && joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND %s " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                } else {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " ORDER BY GIABAN " + temp + " LIMIT " + "?" + " OFFSET " + "?";
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " AND %s " + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (!queryTemp.equals("") && joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND " + "?" + " LIMIT " + "?" + " OFFSET " + "?";
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " AND %s " + " LIMIT " + "?" + " OFFSET " + "?";
                } else {
                    query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + "WHERE HANG = " + " ? " + " LIMIT " + "?" + " OFFSET " + "?";
                }
            }
            String sqlQuery = String.format(query, joinString);
            PreparedStatement ps = DBConnect.getInstance().get(sqlQuery);
            for (String x : mapTemp.get("GIABAN")) {
                map.put("GIABAN", x);
            }
            ps.setString(1, hangsx);
            int count = 2;
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setString(count++, queryTemp);
                } else if (!queryTemp.equals("") && joinString.equals("")) {
                    ps.setString(count++, queryTemp);
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                } else {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setString(count++, queryTemp);
                } else if (!queryTemp.equals("") && joinString.equals("")) {
                    ps.setString(count++, queryTemp);
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                } else {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            }
            for (String x : map.values()) {
                ps.setString(count++, x);
            }
            if (temp != null) {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (joinString.equals("") && !queryTemp.equals("")) {
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            } else {
                if (!joinString.equals("") && !queryTemp.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                } else if (!queryTemp.equals("") && joinString.equals("")) {
                } else if (queryTemp.equals("") && !joinString.equals("")) {
                    ps.setInt(count++, limit);
                    ps.setInt(count++, offset);
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductWithStatus product = new ProductWithStatus(rs.getString(1),
                        rs.getString(2).toLowerCase(),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalPageByProducer(String producer) {
        try {
            String query = "select count(*) as total from thongtinlaptop where hang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, producer);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ProductWithStatus> search(String txt) {
        ArrayList<ProductWithStatus> result = new ArrayList();
        try {
            String query = "select tt.MALAPTOP, tt.LINKHINH1, tt.TENLAPTOP, tt.GIABAN, kh.TONKHO from thongtinlaptop tt JOIN khohang kh on tt.MALAPTOP = kh.MALAPTOP " + " WHERE " + " TENLAPTOP " + " LIKE ? "
                    + " OR " + " HANG " + " LIKE ? " + " OR " + " SERIES " + " LIKE ? " +
                    " OR " + " MAU " + " LIKE ? " + " OR " + " CPU " + " LIKE ? " + " OR " + " VGA "
                    + " LIKE ? " + " OR " + " RAM " + " LIKE ? " + " OR " + " OCUNG " + " LIKE ?";
            int count = query.length() - query.replace("?", "").length();
            PreparedStatement ps = DBConnect.getInstance().get(query);
            for (int i = 1; i <= count; i++) {
                ps.setString(i, "%" + txt + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductWithStatus product = new ProductWithStatus(rs.getString(1),
                        rs.getString(2).toLowerCase(),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalPageSearch(String txt) {
        try {
            String query = "select count(*) as total from THONGTINLAPTOP " + " WHERE " + " TENLAPTOP " + " LIKE ? "
                    + " OR " + " HANG " + " LIKE ? " + " OR " + " SERIES " + " LIKE ? " +
                    " OR " + " MAU " + " LIKE ? " + " OR " + " CPU " + " LIKE ? " + " OR " + " VGA "
                    + " LIKE ? " + " OR " + " RAM " + " LIKE ? " + " OR " + " OCUNG " + " LIKE ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            int count = query.length() - query.replace("?", "").length();
            for (int i = 1; i <= count; i++) {
                ps.setString(i, "%" + txt + "%");
            }
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> searchClick(String txt) {
        ArrayList<Product> result = new ArrayList();
        try {
            String query = "select * from THONGTINLAPTOP " + " WHERE " + " TENLAPTOP " + " LIKE ? "
                    + " OR " + " HANG " + " LIKE ? " + " OR " + " SERIES " + " LIKE ? " +
                    " OR " + " MAU " + " LIKE ? " + " OR " + " CPU " + " LIKE ? " + " OR " + " VGA "
                    + " LIKE ? " + " OR " + " RAM " + " LIKE ? " + " OR " + " OCUNG " + " LIKE ?";
            int count = query.length() - query.replace("?", "").length();
            PreparedStatement ps = DBConnect.getInstance().get(query);
            for (int i = 1; i <= count; i++) {
                ps.setString(i, "%" + txt + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19));
                result.add(product);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
