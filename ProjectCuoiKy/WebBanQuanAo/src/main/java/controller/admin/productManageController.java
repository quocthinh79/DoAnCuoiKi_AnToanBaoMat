package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Beans.Account;
import Beans.Product;
import Services.ProductServices;
import Services.UploadService;
import api.object_reponse.InfoFormAddProduct;
import utils.ParseToBean;
import utils.Payload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Beans.Account;
import Beans.Product;
import Services.ProductServices;
import Services.UploadService;
import api.object_reponse.InfoFormAddProduct;
import utils.ParseToBean;
import utils.Payload;

@WebServlet(name = "productManageController", urlPatterns = {"/admin-product-manage"})
@MultipartConfig()
public class productManageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account == null || !(account.getRole().equals("ADMIN"))) {
//            response.sendRedirect("login");
//        } else {
        String base = request.getServletContext().getContextPath();
        InfoFormAddProduct infoFormAddProduct = ProductServices.getInfoFormAddProduct();
        request.setAttribute("infoForm", infoFormAddProduct);
        request.setAttribute("base", base);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product-manage.jsp");
        request.setAttribute("subTabName", "manageProduct");
        request.setAttribute("tabName", "manage");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String codeProduct = request.getParameter("code-product");
        String name_product = request.getParameter("name-product");
        String brand = request.getParameter("brand");
        double price_product = Double.parseDouble(request.getParameter("price-product"));
        String description = request.getParameter("description");
        String[] tag_product = request.getParameterValues("tag-product");
        String[] color_product = request.getParameterValues("color-product");
        String[] size_product = request.getParameterValues("size-product");
        // save thumbnail
        Part thumbnail = request.getPart("thumbnail");
        String nameCreator = account.getUserName();
        List<Part> detailImg = (List<Part>) request.getParts();
        String readPath = request.getServletContext().getRealPath("/assets/uploads");
        String thumbnailPath = UploadService.uploadFile(thumbnail, readPath);
        String listImagePath[] = new String[detailImg.size()];
        // save list image detail product
        for (int i = 0; i < detailImg.size(); i++) {
            if (detailImg.get(i).getName().equals("images")) {
                listImagePath[i] = UploadService.uploadFile(detailImg.get(i), readPath);
            }
        }
        try {
            String message = ProductServices.addProduct(codeProduct, name_product, brand, price_product, description,
                    tag_product, color_product, size_product, thumbnailPath, listImagePath, nameCreator);
            request.setAttribute("message", message);
            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeProduct = request.getParameter("codeProduct");
		String pathBase = getServletContext().getRealPath("/");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		String message = ProductServices.deleteProduct(codeProduct, pathBase);
		PrintWriter pw = response.getWriter();
		pw.println(message);
		pw.flush();
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String body = Payload.getBody(request);
		Gson gson = new Gson();
//		body=gson.toJson(body);
		Map<String, String> result = gson.fromJson(body, Map.class);
		Product product = ParseToBean.partToBean(result);
		product.setThumbnail("///");
		String message = ProductServices.updateProduct(product);
		PrintWriter rw = response.getWriter();
		rw.println(message);
		rw.flush();
	}
}
