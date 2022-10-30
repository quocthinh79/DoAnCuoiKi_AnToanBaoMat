package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.AdminDao;

import java.util.ArrayList;
import java.util.List;

public class AdminServices {
    private static AdminServices instance;

    private AdminServices() {

    }

    public static AdminServices getInstance() {
        if (instance == null) {
            instance = new AdminServices();
        }
        return instance;
    }

    public String insertLapTop(String maLapTop, String tenLaptop, String hang, int giaBan, String series, String mau, String cpu, String vga, String ram, String kichThuocManHinh, String oCung, String banPhim, String pin, String khoiLuong, String linkHinh1, String linkHinh2, String linkHinh3, String linkHinh4, String linkHinh5) {
        return AdminDao.getInstance().insertLapTop(maLapTop, tenLaptop, hang, giaBan, series, mau, cpu, vga, ram, kichThuocManHinh, oCung, banPhim, pin, khoiLuong, linkHinh1, linkHinh2, linkHinh3, linkHinh4, linkHinh5);
    }

    public String updateLaptop(String maLapTop, String tenLaptop, String hang, int giaBan, String series, String mau, String cpu, String vga, String ram, String kichThuocManHinh, String oCung, String banPhim, String pin, String khoiLuong, String linkHinh1, String linkHinh2, String linkHinh3, String linkHinh4, String linkHinh5, String dieuKien) {
        return AdminDao.getInstance().updateLaptop(maLapTop, tenLaptop, hang, giaBan, series, mau, cpu, vga, ram, kichThuocManHinh, oCung, banPhim, pin, khoiLuong, linkHinh1, linkHinh2, linkHinh3, linkHinh4, linkHinh5, dieuKien);
    }

    public int deleteLaptop(String dieuKien) {
        return AdminDao.getInstance().deleteLaptop(dieuKien);
    }

    // Phân trang hãng sản xuất
    public ArrayList getAllManufacturer(int limit, int page) {
        return AdminDao.getInstance().getAllManufacturer(limit, page);
    }

    public int getTotalManufacturer() {
        return AdminDao.getInstance().getTotalManufacturer();
    }

    // Thêm xóa sửa hãng sản xuất
    public String insertHangSx(String tenHang, String quocGia, String poster, String logoVuong, String logoNgang, String slogan) {
        return AdminDao.getInstance().insertHangSx(tenHang, quocGia, poster, logoVuong, logoNgang, slogan);
    }

    public String updateHangSx(String tenHang, String quocGia, String poster, String logoVuong, String logoNgang, String slogan, String dieuKien) {
        return AdminDao.getInstance().updateHangSx(tenHang, quocGia, poster, logoVuong, logoNgang, slogan, dieuKien);
    }

    public int deleteHangSx(String dieuKien) {
        return AdminDao.getInstance().deleteHangSx(dieuKien);
    }

    // Phân trang kho hàng
    public ArrayList getAllWareHouse(int limit, int page) {
        return AdminDao.getInstance().getAllWareHouse(limit, page);
    }

    public int getTotalPageWareHouse() {
        return AdminDao.getInstance().getTotalPageWareHouse();
    }

    // Thêm xóa sửa hãng sản xuất
    public String insertKho(String maLaptop, int slNhap, int slXuat, int slTon) {
        return AdminDao.getInstance().insertKho(maLaptop, slNhap, slXuat, slTon);
    }

    public String updateKho(String maLaptop, int slNhap, int slXuat, int slTon, String dieuKien) {
        return AdminDao.getInstance().updateKho(maLaptop, slNhap, slXuat, slTon, dieuKien);
    }

    public int deleteKho(String dieuKien) {
        return AdminDao.getInstance().deleteKho(dieuKien);
    }

    // Phân trang tài khoản
    public ArrayList getAllAccount(int limit, int page) {
        return AdminDao.getInstance().getAllAccount(limit, page);
    }

    public int getTotalPageAccount() {
        return AdminDao.getInstance().getTotalPageAccount();
    }

    // Thêm xóa sửa hãng sản xuất
    public String insertAccount(String hoTen, String username, String pass, String diaChi, String email) {
        return AdminDao.getInstance().insertAccount(hoTen, username, pass, diaChi, email);
    }

    public String updateAccount(String hoTen, String username, String pass, String diaChi, String email, int dieuKien) {
        return AdminDao.getInstance().updateAccount(hoTen, username, pass, diaChi, email, dieuKien);
    }

    public int deleteAccount(int dieuKien) {
        return AdminDao.getInstance().deleteAccount(dieuKien);
    }

    // Phân trang tài khoản
    public ArrayList getAllPermission() {
        return AdminDao.getInstance().getAllPermission();
    }

    public ArrayList getAllPermission(int limit, int page) {
        return AdminDao.getInstance().getAllPermission(limit, page);
    }

    public int getTotalPagePermission() {
        return AdminDao.getInstance().getTotalPagePermission();
    }

    // Thêm xóa sửa phân quyền
    public String insertPermission(int id, String quyen) {
        return AdminDao.getInstance().insertPermission(id, quyen);
    }

    public String updatePermission(String quyen, int dieuKien) {
        return AdminDao.getInstance().updatePermission(quyen, dieuKien);
    }

    public int deletePermission(int dieuKien) {
        return AdminDao.getInstance().deletePermission(dieuKien);
    }

    // Phân trang giỏ hàng
    public ArrayList getAllCart(int limit, int page) {
        return AdminDao.getInstance().getAllCart(limit, page);
    }

    public int getTotalPageCart() {
        return AdminDao.getInstance().getTotalPageCart();
    }

    // Thêm xóa sửa giỏ hàng
    public String insertCart(String maGioHang, int makh, String ngayXuatGioHang, long triGia) {
        return AdminDao.getInstance().insertCart(maGioHang, makh, ngayXuatGioHang, triGia);
    }

    public String updateCart(String maGioHang, int makh, String ngayXuatGioHang, long triGia, String dieuKien) {
        return AdminDao.getInstance().updateCart(maGioHang, makh, ngayXuatGioHang, triGia, dieuKien);
    }

    public int deleteCart(String dieuKien) {
        return AdminDao.getInstance().deleteCart(dieuKien);
    }

    // Phân trang chi tiết giỏ hàng
    public ArrayList getAllCartDetail(int limit, int page) {
        return AdminDao.getInstance().getAllCartDetail(limit, page);
    }

    public int getTotalPageCartDetail() {
        return AdminDao.getInstance().getTotalPageCartDetail();
    }

    // Thêm xóa sửa chi tiết giỏ hàng
    public String insertCartDetail(String maGioHang, String maLaptop, int soLuong) {
        return AdminDao.getInstance().insertCartDetail(maGioHang, maLaptop, soLuong);
    }

    public String updateCartDetail(String maGioHang, String maLaptop, int soLuong, String dieuKien) {
        return AdminDao.getInstance().updateCartDetail(maGioHang, maLaptop, soLuong, dieuKien);
    }

    public int deleteCartDetail(String dieuKien) {
        return AdminDao.getInstance().deleteCartDetail(dieuKien);
    }

    // Phần trên cùng trang admin
    public ArrayList getAllManufacturer() {
        return AdminDao.getInstance().getAllManufacturer();
    }
}
