package api.account;

import Beans.Account;
import Dao.AccountDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeleteAccount", value = "/api-delete-account")
public class DeleteAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAccount = request.getParameter("id_account");
        boolean delete = AccountDao.deleteAccount(idAccount);
        String msg = delete ? "Xóa tài khoản thành công!" : "Xóa tài khoản thất bại!";
        response.setContentType("application/text");
        Gson gson = new Gson();
        String mess = gson.toJson(msg);
        PrintWriter pw = response.getWriter();
        pw.println(mess);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
