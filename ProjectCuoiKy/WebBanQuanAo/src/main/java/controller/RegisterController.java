package controller;

import Services.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerController", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");

        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("userName", userName);

        request.setAttribute("pageName", "Đăng ký");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        int checkRegister = RegisterService.addRegister(lastName, firstName, phone, email, userName, password);
        String message = "";
        String href = "";
        String hrefName = "";
        if (checkRegister == 0) {
            message = "Vui lòng click vào link trong email để xác thực đăng ký";
            request.setAttribute("message", message);
            request.setAttribute("href", href);
            request.setAttribute("hrefName", hrefName);
            request.setAttribute("pageName", "Thông báo");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/notification.jsp");
            rd.forward(request, response);
        } else {
            switch (checkRegister) {
                case 1:
                    message = "Đã xảy ra lỗi trong quá trình đăng ký vui lòng thử lại sau";
                    break;
                case 2:
                    message = "Email đã được sử dụng";
                    break;
                default:
                    message = "Tên tài khoản đã được sử dụng, Vui lòng chọn tên tài khoản khác";
                    break;
            }
            request.setAttribute("message", message);
            doGet(request, response);
        }
    }
}
