package controller.admin;

import Beans.Brand;
import Dao.BrandDao;
import Dao.ColorDao;
import Services.AccountServices;
import Services.BrandService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "brandManageController", value = "/admin-brand-manage")
public class BrandManageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account == null || !(account.getRole().equals("ADMIN"))) {
//            response.sendRedirect("login");
//        } else {
        String base = request.getServletContext().getContextPath();
        request.setAttribute("base", base);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/brand-manager.jsp");
        request.setAttribute("subTabName", "manageBrand");
        request.setAttribute("tabName", "manage");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idBrand = request.getParameter("id-brand");
        String brandName = request.getParameter("brand-name");
        String action = request.getParameter("action");
        String message = "";
        switch (action) {
            case "add": {
                boolean status = BrandService.addBrand(idBrand, brandName);
                message = status ? "Thêm nhãn hiệu thành công" : "Thêm nhãn hiệu thất bại, vui lòng kiểm tra lại";
                break;
            }
            case "delete": {
                boolean status = BrandService.deleteBrand(idBrand);
                message = status ? "Xóa nhãn hiệu thành công" : "Xóa nhãn hiệu thất bại vui lòng kiểm tra lại";
                break;
            }
            case "edit": {
                boolean status = BrandService.updateBrand(idBrand, brandName);
                message = status ? "Chỉnh sửa nhãn hiệu thành công" : "Chỉnh sửa nhãn hiệu thất bại vui lòng kiểm tra lại";
                break;
            }
            default:
                break;
        }
        request.setAttribute("message", message);
        doGet(request, response);
    }
}
