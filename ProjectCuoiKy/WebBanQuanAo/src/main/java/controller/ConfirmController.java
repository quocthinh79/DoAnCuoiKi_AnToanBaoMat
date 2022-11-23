package controller;

import Dao.AccountDao;

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
        String message = "";
        String href = "";
        String hrefName = "";
        message = "Chúc mừng bạn đăng ký thành công, ";
        href = "login";
        hrefName = "Đăng nhập ngay";
        request.setAttribute("message", message);
        request.setAttribute("href", href);
        request.setAttribute("hrefName", hrefName);
        request.setAttribute("pageName", "Thông báo");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/notification.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
