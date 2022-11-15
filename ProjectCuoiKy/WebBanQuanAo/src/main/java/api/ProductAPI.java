package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Beans.Product;
import Beans.ProductNew;
import Dao.ProductDao;
import Services.ProductServices;

@WebServlet(name = "ProductAPI", urlPatterns = { "/api-get-product", "/check-code-product", "/delete-thumbail",
		"/delete-images", "/get-product-edit", "/update-thumbnail" })
@MultipartConfig
public class ProductAPI extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		switch (path) {
		case "/WebBanQuanAo/api-get-product": {
			List<Product> listProductAPI = ProductDao.loadProduct();
			Gson gson = new Gson();
			String list = gson.toJson(listProductAPI);
			PrintWriter pw = response.getWriter();
			pw.println(list);
			pw.flush();
			break;
		}
		case "/WebBanQuanAo/check-code-product": {
			String codeProduct = request.getParameter("codeProduct");
			PrintWriter pw = response.getWriter();
			pw.println(ProductServices.checkCodeProduct(codeProduct));
			pw.flush();
			break;
		}
		case "/WebBanQuanAo/get-product-edit": {
			String productId = request.getParameter("codeProduct");
			ProductNew productNew = ProductServices.getProductEdit(productId);
			Gson gson = new Gson();
			String productString = gson.toJson(productNew);
			PrintWriter pw = response.getWriter();
			pw.println(productString);
			pw.flush();
			break;
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		switch (uri) {
		case "/WebBanQuanAo/update-thumbnail":
			String codeProduct = request.getParameter("codeProduct");
			String oldPath = request.getParameter("oldPath");
			Part filePart = request.getPart("thumbnail");
			String readPath = request.getServletContext().getRealPath("/assets/imgs/product-imgs");
			ProductServices.updateThumbnail(codeProduct, filePart, readPath, oldPath);
			break;

		default:
			break;
		}
	}
}
