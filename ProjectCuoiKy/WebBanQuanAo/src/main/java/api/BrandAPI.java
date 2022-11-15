package api;

import Beans.Brand;
import Beans.Color;
import Dao.ColorDao;
import Services.BrandService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author : Cong-Phuc, Nguyen
 * @lastest_update : 8/12/2022
 **/
@WebServlet(name = "GetBrand", urlPatterns = {"/api-brand"})
public class BrandAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brandList = BrandService.getBrandList();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String list = gson.toJson(brandList);
        PrintWriter pw = response.getWriter();
        pw.println(list);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String idBrand = request.getParameter("id_brand");
        switch (action) {
            case "delete": {
                boolean status = BrandService.deleteBrand(idBrand);
                PrintWriter pw = response.getWriter();
                String message = status ? "Xóa nhãn hiệu thành công" : "Xóa nhãn hiệu thất bại vui lòng kiểm tra lại";
                pw.println(message);
                pw.flush();
                break;
            }
            case "edit": {
                String brandName = request.getParameter("brand-name");
                boolean status = BrandService.updateBrand(idBrand, brandName);
                PrintWriter pw = response.getWriter();
                String message = status ? "Chỉnh sửa nhãn hiệu thành công" : "Chỉnh sửa nhãn hiệu thất bại vui lòng kiểm tra lại";
                pw.println(message);
                pw.flush();
                break;
            }
            default:
                break;
        }
    }
}
