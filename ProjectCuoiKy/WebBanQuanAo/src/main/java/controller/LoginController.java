package controller;

//import Beans.Account;

import Beans.Account;
import Services.AccountServices;
import Services.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account1 = (Account) session.getAttribute("account");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (account1 == null) {
            request.setAttribute("pageName", "Đăng nhập");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        } else {
            if (account1.getRole().equals("ADMIN")) {
                response.sendRedirect("admin-dash-board");
            } else {
                response.sendRedirect("home");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        Account account = AccountServices.getAccount(userName, password, true);
        if (account == null) {
            request.setAttribute("userName", userName);
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
//            request.setAttribute("pageName", "Quên mật khẩu");
//            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
//            rd.forward(request, response);
            doGet(request, response);
        } else {
            session.setAttribute("account", account);
            Cookie[] cookies = request.getCookies();
            String productID_nu = null;
            String color_nu = null;
            String size_nu = null;
            String quantity_nu = null;
            for (Cookie ck : cookies) {
                if (ck.getName().equals("productID-nu")) {
                    productID_nu = ck.getValue().trim();
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                    break;
                }
            }
            if (productID_nu != null) {
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("color-nu")) {
                        color_nu = ck.getValue().trim();
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                    if (ck.getName().equals("size-nu")) {
                        size_nu = ck.getValue().trim();
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                    if (ck.getName().equals("quantity-nu")) {
                        quantity_nu = ck.getValue().trim();
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                }
                CartService.addToCart(account.getUserID(), productID_nu, Integer.parseInt(quantity_nu), color_nu, size_nu);
            }

            if (rememberme != null) {
                Cookie cookieUserName = new Cookie("username", account.getUserName());
                Cookie cookiePassword = new Cookie("password", account.getPassWord());
                Cookie cookieIdUser = new Cookie("idUser", "" + account.getUserID());
                cookieUserName.setMaxAge(60 * 60 * 24);
                cookiePassword.setMaxAge(60 * 60 * 24);
                cookieIdUser.setMaxAge(60 * 60 * 24);
                response.addCookie(cookieUserName);
                response.addCookie(cookiePassword);
                response.addCookie(cookieIdUser);
            }
            if (account.getRole().equals("ADMIN")) {
                response.sendRedirect("admin-dash-board");
            } else {
                response.sendRedirect("home");
            }
        }
    }
}
