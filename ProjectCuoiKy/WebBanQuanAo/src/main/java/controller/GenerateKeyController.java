package controller;

import Beans.Account;
import Dao.AccountDao;
import Services.AccountServices;
import Services.SendMailService;
import cipher.DSA;
import cipher.ReadAndWriteFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GenerateKeyController", value = "/GenerateKeyController")
public class GenerateKeyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account1 = (Account) session.getAttribute("account");
        String username = account1.getUserName();
        String pw = request.getParameter("pass-generate-key");
        Account checkAcc = AccountServices.getAccount(username, pw, true);

        if (checkAcc != null) {
            DSA dsa = new DSA();
            String privateKey = dsa.byteToString(dsa.getPrivateKey().getEncoded());
            String publicKey = dsa.byteToString(dsa.getPublicKey().getEncoded());
            AccountDao.addPublicKey(username, publicKey);
            String keyPath = request.getServletContext().getRealPath("/assets/privateKey.bin");
            ReadAndWriteFile.getInstance().writeKeyToFile(privateKey, keyPath);
            Account account = AccountDao.getById(username);
            String email = account.getEmail();
            String messagesenmail = "Day la khoa cua ban.Vui long khong chia se cho nguoi khac";
            boolean sendsuccess = SendMailService.sendMailwithFile(email, "Private Key ", messagesenmail, keyPath);
            System.out.println("send mail to " + email + " " + sendsuccess);
        } else {
            request.setAttribute("error", "Sai mật khẩu");
            System.out.println("Sai tài khoản");
        }
        request.setAttribute("pageName", "Tài khoản");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/myAccount.jsp");
        rd.forward(request, response);
    }
}
