package controller;

import Beans.Product;
import Services.CartService;
import Services.ProductServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "singleProductController", value = "/single-product")
public class SingleProductController extends HttpServlet {
    private static String id = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        Product product = ProductServices.getInformationProduct(productId);
        this.id = productId;
        request.setAttribute("product", product);
        request.setAttribute("pageName", "Sản phẩm");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/singleProduct.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
