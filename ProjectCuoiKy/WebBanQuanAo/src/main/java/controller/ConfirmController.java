package controller;

import Beans.Account;
import Dao.AccountDao;
import Services.SendMailService;
import cipher.DSA;
import cipher.ReadAndWriteFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmController", value = "/confirm")
public class ConfirmController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String token = request.getParameter("token");

        AccountDao.updateStateRegister(id, token, AccountDao.RegisterState.SUCCESS);
//      Lấy ra tên tài khoản đã đăng ký thành công
        String username = AccountDao.getUserFromTableDANGKY(id);
        Account account = AccountDao.getById(username);

//      Tạo public và private key
        DSA dsa = new DSA();
        String privateKey = dsa.byteToString(dsa.getPrivateKey().getEncoded());
        String publicKey = dsa.byteToString(dsa.getPublicKey().getEncoded());
//      Lưu trữ publicKey vào database
        AccountDao.addPublicKey(username, publicKey);
//      Gửi cho người dùng chuỗi privateKey

        String message = "";
        String href = "";
        String hrefName = "";
        String messagesenmail = "Day la khoa cua ban.Vui long khong chia se cho nguoi khac";

        message = "Chúc mừng bạn đăng ký thành công. Chúng tôi đã gửi private key tới mail của bạn.";
        href = "login";
        hrefName = "Đăng nhập ngay";
        request.setAttribute("message", message);
        request.setAttribute("href", href);
        request.setAttribute("hrefName", hrefName);
        request.setAttribute("pageName", "Thông báo");

        String keyPath = request.getServletContext().getRealPath("/assets/privateKey.bin");
        ReadAndWriteFile.getInstance().writeKeyToFile(privateKey,keyPath);

        String email = account.getEmail();
        boolean sendsuccess = SendMailService.sendMailwithFile(email,"private key xác thực thanh toán của Shop bán quần áo",messagesenmail,keyPath);

        System.out.println("send mail to " +email + " " +sendsuccess);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/notification.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
