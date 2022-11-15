package api;

import Beans.Color;
import Beans.Image;
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

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Image;
import Dao.ColorDao;
import Services.ProductServices;

@WebServlet(name = "ImageAPI", urlPatterns = { "/api-image" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100)
public class ImageAPI extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Image> images = ProductServices.getAllImage();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String list = gson.toJson(images);
		PrintWriter pw = response.getWriter();
		pw.println(list);
		pw.flush();
	}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String idProduct = request.getParameter("id");
        String pathImg = request.getParameter("pathImg");
        String idColor = request.getParameter("idColor");
        switch (action) {
            case "delete": {
                boolean status = ProductDao.deleteImageDetail(idProduct, pathImg, idColor);
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