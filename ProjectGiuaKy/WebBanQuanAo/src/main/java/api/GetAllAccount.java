package api;

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

@WebServlet(name = "GetAllAccount", value = "/api-get-user")
public class GetAllAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        List<Account> listAccount = AccountDao.getListUser();
        response.setContentType("application/json");
        Gson gson = new Gson();
        String list = gson.toJson(listAccount);
        PrintWriter pw = response.getWriter();
        pw.println(list);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}