package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.*;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
    private static AdminDao instance;

    public static AdminDao getInstance() {
        if (instance == null) {
            instance = new AdminDao();
        }
        return instance;
    }

    public String insertLapTop(String maLapTop, String tenLaptop, String hang, int giaBan, String series, String mau, String cpu, String vga, String ram, String kichThuocManHinh, String oCung, String banPhim, String pin, String khoiLuong, String linkHinh1, String linkHinh2, String linkHinh3, String linkHinh4, String linkHinh5) {
        try {
            String query = "INSERT INTO thongtinlaptop VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maLapTop);
            ps.setString(2, tenLaptop);
            ps.setString(3, hang);
            ps.setInt(4, giaBan);
            ps.setString(5, series);
            ps.setString(6, mau);
            ps.setString(7, cpu);
            ps.setString(8, vga);
            ps.setString(9, ram);
            ps.setString(10, kichThuocManHinh);
            ps.setString(11, oCung);
            ps.setString(12, banPhim);
            ps.setString(13, pin);
            ps.setString(14, khoiLuong);
            ps.setString(15, linkHinh1);
            ps.setString(16, linkHinh2);
            ps.setString(17, linkHinh3);
            ps.setString(18, linkHinh4);
            ps.setString(19, linkHinh5);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            insertKho(maLapTop, 5, 0, 5);
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateLaptop(String maLapTop, String tenLaptop, String hang, int giaBan, String series, String mau, String cpu, String vga, String ram, String kichThuocManHinh, String oCung, String banPhim, String pin, String khoiLuong, String linkHinh1, String linkHinh2, String linkHinh3, String linkHinh4, String linkHinh5, String dieuKien) {
        try {
            String query = "UPDATE `thongtinlaptop` SET `MALAPTOP`=?,`TENLAPTOP`=?,`HANG`=?,`GIABAN`=?,`SERIES`=?,`MAU`=?,`CPU`=?,`VGA`=?,`RAM`=?,`KICHTHUOCMANHINH`=?,`OCUNG`=?,`BANPHIM`=?,`PIN`=?,`KHOILUONG`=?,`LINKHINH1`=?,`LINKHINH2`=?,`LINKHINH3`=?,`LINKHINH4`=?,`LINKHINH5`=? WHERE MALAPTOP = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maLapTop);
            ps.setString(2, tenLaptop);
            ps.setString(3, hang);
            ps.setInt(4, giaBan);
            ps.setString(5, series);
            ps.setString(6, mau);
            ps.setString(7, cpu);
            ps.setString(8, vga);
            ps.setString(9, ram);
            ps.setString(10, kichThuocManHinh);
            ps.setString(11, oCung);
            ps.setString(12, banPhim);
            ps.setString(13, pin);
            ps.setString(14, khoiLuong);
            ps.setString(15, linkHinh1);
            ps.setString(16, linkHinh2);
            ps.setString(17, linkHinh3);
            ps.setString(18, linkHinh4);
            ps.setString(19, linkHinh5);
            ps.setString(20, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteLaptop(String dieuKien) {
        try {
            deleteKho(dieuKien);
            deleteCartDetail(dieuKien);
            String query = "DELETE FROM `thongtinlaptop` WHERE MALAPTOP = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang Hãng sản xuất
    public ArrayList getAllManufacturer(int limit, int page) {
        ArrayList<Manufacturer> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from hangsx LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listResult.add(manufacturer);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalManufacturer() {
        try {
            String query = "select count(*) as total from hangsx";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa Hãng sản xuất
    public String insertHangSx(String tenHang, String quocGia, String poster, String logoVuong, String logoNgang, String slogan) {
        try {
            String query = "INSERT INTO hangsx VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, tenHang);
            ps.setString(2, quocGia);
            ps.setString(3, poster);
            ps.setString(4, logoVuong);
            ps.setString(5, logoNgang);
            ps.setString(6, slogan);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateHangSx(String tenHang, String quocGia, String poster, String logoVuong, String logoNgang, String slogan, String dieuKien) {
        try {
            String query = "UPDATE `hangsx` SET `TENHANG`=?,`QUOCGIA`=?,`POSTER`=?,`LOGOVUONG`=?,`LOGONGANG`=?,`SLOGAN`=? WHERE `TENHANG`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, tenHang);
            ps.setString(2, quocGia);
            ps.setString(3, poster);
            ps.setString(4, logoVuong);
            ps.setString(5, logoNgang);
            ps.setString(6, slogan);
            ps.setString(7, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteHangSx(String dieuKien) {
        try {
            deleteLaptop(dieuKien);
            String query = "DELETE FROM `hangsx` WHERE TENHANG = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang Hãng sản xuất
    public ArrayList getAllWareHouse(int limit, int page) {
        ArrayList<WareHouse> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from khohang LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                WareHouse wareHouse = new WareHouse(resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4));
                listResult.add(wareHouse);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPageWareHouse() {
        try {
            String query = "select count(*) as total from khohang";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa Kho hàng
    public String insertKho(String maLaptop, int slNhap, int slXuat, int slTon) {
        try {
            String query = "INSERT INTO khohang VALUES (?, ?, ?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maLaptop);
            ps.setInt(2, slNhap);
            ps.setInt(3, slXuat);
            ps.setInt(4, slTon);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateKho(String maLaptop, int slNhap, int slXuat, int slTon, String dieuKien) {
        try {
            String query = "UPDATE `khohang` SET `MALAPTOP`=?,`SLNHAP`=?,`SLXUAT`=?,`TONKHO`=? WHERE `MALAPTOP`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maLaptop);
            ps.setInt(2, slNhap);
            ps.setInt(3, slXuat);
            ps.setInt(4, slTon);
            ps.setString(5, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteKho(String dieuKien) {
        try {
            String query = "DELETE FROM `khohang` WHERE MALAPTOP = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang tài khoản
    public ArrayList getAllAccount(int limit, int page) {
        ArrayList<Account> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from tk LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Account account = new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                listResult.add(account);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPageAccount() {
        try {
            String query = "select count(*) as total from tk";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa tài khoản
    public String insertAccount(String hoTen, String username, String pass, String diaChi, String email) {
        try {
            String query = "INSERT INTO tk VALUES (NULL, ?, ?, ?, ?, ?, NULL)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, hoTen);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, diaChi);
            ps.setString(5, email);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateAccount(String hoTen, String username, String pass, String diaChi, String email, int dieuKien) {
        try {
            String query = "UPDATE `tk` SET `HOTEN`=?,`USERNAME`=?,`PASSWORD`=?,`DIACHI`=?,`EMAIL`=?  WHERE `ID`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, hoTen);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, diaChi);
            ps.setString(5, email);
            ps.setInt(6, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteAccount(int dieuKien) {
        try {
            deletePermission(dieuKien);
            String query = "DELETE FROM `tk` WHERE ID = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang phân quyền
    public ArrayList getAllPermission() {
        ArrayList<Permission> listResult = new ArrayList<>();
        try {
            String query = "select * from phanquyen";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Permission permission = new Permission(resultSet.getInt(1),
                        resultSet.getString(2));
                listResult.add(permission);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList getAllPermission(int limit, int page) {
        ArrayList<Permission> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from phanquyen LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Permission permission = new Permission(resultSet.getInt(1),
                        resultSet.getString(2));
                listResult.add(permission);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPagePermission() {
        try {
            String query = "select count(*) as total from phanquyen";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa tài khoản
    public String insertPermission(int id, String quyen) {
        try {
            String query = "INSERT INTO phanquyen VALUES (?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, id);
            ps.setString(2, quyen);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updatePermission(String quyen, int dieuKien) {
        try {
            String query = "UPDATE `phanquyen` SET `QUYEN`=? WHERE `ID`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, quyen);
            ps.setInt(2, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deletePermission(int dieuKien) {
        try {
            String query = "DELETE FROM `phanquyen` WHERE ID = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang giỏ hàng
    public ArrayList getAllCart(int limit, int page) {
        ArrayList<Cart> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from giohang LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart(resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getLong(4));
                listResult.add(cart);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPageCart() {
        try {
            String query = "select count(*) as total from giohang";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa giỏ hàng
    public String insertCart(String maGioHang, int makh, String ngayXuatGioHang, long triGia) {
        try {
            String query = "INSERT INTO giohang VALUES (?, ?, ?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maGioHang);
            ps.setInt(2, makh);
            ps.setString(3, ngayXuatGioHang);
            ps.setLong(4, triGia);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateCart(String maGioHang, int makh, String ngayXuatGioHang, long triGia, String dieuKien) {
        try {
            String query = "UPDATE `giohang` SET `MAGIOHANG`=?, `MAKH`=?, `NGAYXUATGIOHANG`=?, `TRIGIA`=? WHERE `MAGIOHANG`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maGioHang);
            ps.setInt(2, makh);
            ps.setString(3, ngayXuatGioHang);
            ps.setLong(4, triGia);
            ps.setString(5, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteCart(String dieuKien) {
        try {
            deleteCartDetail(dieuKien);
            String query = "DELETE FROM `giohang` WHERE magiohang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phân trang chi tiết giỏ hàng
    public ArrayList getAllCartDetail(int limit, int page) {
        ArrayList<CartDetail> listResult = new ArrayList<>();
        try {
            int offset = (page - 1) * limit;
            String query = "select * from ctgh LIMIT " + "?" + " OFFSET " + "?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CartDetail cartDetail = new CartDetail(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                listResult.add(cartDetail);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPageCartDetail() {
        try {
            String query = "select count(*) as total from ctgh";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("total");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm, xóa, sửa chi tiết giỏ hàng
    public String insertCartDetail(String maGioHang, String maLaptop, int soLuong) {
        try {
            String query = "INSERT INTO ctgh VALUES (?, ?, ?)";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maGioHang);
            ps.setString(2, maLaptop);
            ps.setInt(3, soLuong);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateCartDetail(String maGioHang, String maLaptop, int soLuong, String dieuKien) {
        try {
            String query = "UPDATE `ctgh` SET `MAGIOHANG`=?, `MALAPTOP`=?, `SOLUONG`=? WHERE `MAGIOHANG`=?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, maGioHang);
            ps.setString(2, maLaptop);
            ps.setInt(3, soLuong);
            ps.setString(4, dieuKien);
            String result = "Success";
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteCartDetail(String dieuKien) {
        try {
            String query = "DELETE FROM `ctgh` WHERE magiohang = ?";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, dieuKien);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phần trên cùng trang admin
    public ArrayList getAllManufacturer() {
        ArrayList<HeaderAdmin> listResult = new ArrayList<>();
        try {
            String query = "select a.hang, a.logovuong, sum(kho.SLNHAP), sum(kho.SLXUAT), sum(kho.TONKHO) FROM (SELECT tt.MALAPTOP, tt.HANG, h.LOGOVUONG FROM thongtinlaptop tt join hangsx h on tt.HANG = h.TENHANG) as a JOIN khohang kho on kho.MALAPTOP = a.MALAPTOP GROUP by a.hang";
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                HeaderAdmin headerAdmin = new HeaderAdmin(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                listResult.add(headerAdmin);
            }
            return listResult;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
