package controller;

import Beans.Account;
import Services.AccountServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "home", urlPatterns = {"","/home"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account1 = (Account) session.getAttribute("account");
        if (account1 == null) {
            Cookie[] cookies = request.getCookies();
            String userName = "";
            String password = "";
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    userName = c.getValue();
                }
                if (c.getName().equals("password")) {
                    password = c.getValue();
                }
            }
            if (userName != "" && password != "") {
                Account account = AccountServices.getAccount(userName, password, false);
                session.setAttribute("account", account);
            }
        }
        session.setAttribute("day", getDay());
        request.setAttribute("pageName", "Trang chủ");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public String getDay() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate date = LocalDate.now();
        DayOfWeek dOW = date.getDayOfWeek();
        String thu = "";
        switch (dOW.getValue()) {
            case 7: {
                thu = "Chủ nhật";
                break;
            }
            case 1: {
                thu = "Thứ hai";
                break;
            }
            case 2: {
                thu = "Thứ ba";
                break;
            }
            case 3: {
                thu = "Thứ tư";
                break;
            }
            case 4: {
                thu = "Thứ năm";
                break;
            }
            case 5: {
                thu = "Thứ sáu";
                break;
            }
            case 6: {
                thu = "Thứ bảy";
                break;
            }
        }
        String result = thu + ", " + date.format(format);
        return result;
    }
}
