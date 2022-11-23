package api;

import Beans.Account;
import Beans.Color;
import Beans.Product;
import Dao.AccountDao;
import Dao.ColorDao;
import Dao.ProductDao;
import Services.ProductServices;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetColor", urlPatterns = {"/api-color"})
public class ColorAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Color> listColors = ColorDao.getColorList();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String list = gson.toJson(listColors);
        PrintWriter pw = response.getWriter();
        pw.println(list);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String idColor = request.getParameter("id_color");
        switch (action) {
            case "delete": {
                boolean status = ColorDao.deleteColor(idColor);
                PrintWriter pw = response.getWriter();
                String message = status ? "Xóa màu thành công" : "Xóa màu thất bại vui lòng kiểm tra lại";
                pw.println(message);
                pw.flush();
                break;
            }
            case "edit": {
                String colorName = request.getParameter("color-name");
                boolean status = ColorDao.editColor(idColor, colorName);
                PrintWriter pw = response.getWriter();
                String message = status ? "Chỉnh sửa màu thành công" : "Chỉnh sửa màu thất bại vui lòng kiểm tra lại";
                pw.println(message);
                pw.flush();
                break;
            }
            default:
                break;
        }
    }
}