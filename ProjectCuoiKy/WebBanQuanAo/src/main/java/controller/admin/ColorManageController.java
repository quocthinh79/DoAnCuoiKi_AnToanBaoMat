package controller.admin;

import Dao.ColorDao;
import Services.AccountServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ColorManageController", value = "/admin-color-manage")
public class ColorManageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account == null || !(account.getRole().equals("ADMIN"))) {
//            response.sendRedirect("login");
//        } else {
        String base = request.getServletContext().getContextPath();
        request.setAttribute("base", base);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/color-manage.jsp");
        request.setAttribute("subTabName", "manageColor");
        request.setAttribute("tabName", "manage");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idColor = request.getParameter("id-color");
        String colorName = request.getParameter("color-name");
        String action = request.getParameter("action");
        String message = "";
        switch (action) {
            case "add": {
                boolean status = ColorDao.addColor(idColor, colorName);
                message = status ? "Thêm màu thành công" : "Thêm màu thất bại, vui lòng kiểm tra lại";
                break;
            }
            case "delete": {
                boolean status = ColorDao.deleteColor(idColor);
                message = status ? "Xóa màu thành công" : "Xóa màu thất bại vui lòng kiểm tra lại";
                break;
            }
            case "edit": {
                boolean status = ColorDao.editColor(idColor, colorName);
                message = status ? "Chỉnh sửa màu thành công" : "Chỉnh sửa màu thất bại vui lòng kiểm tra lại";
                break;
            }
            default:
                break;
        }
        request.setAttribute("message", message);
        doGet(request, response);
    }
}
