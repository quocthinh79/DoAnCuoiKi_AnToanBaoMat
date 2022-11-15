package Dao;

import Beans.*;
import api.object_reponse.InfoDelete;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    public static int createNewPayment(int ma_gio, String dia_chi, String sdt, String nguoi_nhan, double thanh_tien) {
        int ma_hoa_don = -1;
        String sql = "INSERT INTO HOADON(MA_GIO, DIA_CHI_GH, SDT, NGUOI_NHAN, THANH_TIEN, TRANG_THAI) VALUES(?, ?, ?, ?, ?, 'DAXACNHAN')";
        Connection connection = Connect.getInstance().getConnection();
        PreparedStatement prep = null;
        ResultSet resultSet = null;
        try {
            prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, ma_gio);
            prep.setString(2, dia_chi);
            prep.setString(3, sdt);
            prep.setString(4, nguoi_nhan);
            prep.setDouble(5, thanh_tien);
            int result = prep.executeUpdate();
            if (result > 0) {
                resultSet = prep.getGeneratedKeys();
                while (resultSet.next()) {
                    ma_hoa_don = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma_hoa_don;
    }

    public static boolean addPaymentDetail(int ma_hoa_don, String ma_sp, String ma_ct_kich_thuoc, String ma_ct_mau, int so_luong) {
        String sql = "INSERT INTO CTHD(MA_HOA_DON, MA_SP, KICH_THUOC, MAU_SAC, SO_LUONG) VALUES(?, ?, ?, ?, ?)";
        Connection connection = Connect.getInstance().getConnection();
        PreparedStatement prep = null;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, ma_hoa_don);
            prep.setString(2, ma_sp);
            prep.setString(3, ma_ct_kich_thuoc);
            prep.setString(4, ma_ct_mau);
            prep.setInt(5, so_luong);
            return prep.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
