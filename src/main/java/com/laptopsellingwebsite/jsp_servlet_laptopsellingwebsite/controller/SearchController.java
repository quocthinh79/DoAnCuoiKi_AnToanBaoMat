package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.ProductWithStatus;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "SearchController", value = "/Search")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductWithStatus> list = null;
        String txt = request.getParameter("txt");
        response.setContentType("text/html;charset=UTF-8");
        list = ProductService.getInstance().search(txt);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        PrintWriter out = response.getWriter();
        for (ProductWithStatus x : list) {
            if (x.getTrangThai() == 0) {
                out.println("<div class=\"hover-all-product\">\n" +
                        "                        <a class=\"all-product-item\" href=\"" + request.getContextPath() + "/Product?id=" + x.getMaLapTop() + "\">\n" +
                        "                            <div class=\"img-all-product-item\"\n" +
                        "                                 style=\"background-image: url('" + x.getImage() + "')\">\n" +
                        "                            </div>\n" +
                        "                            <div class=\"status\">HẾT HÀNG</div>\n" +
                        "                            <div class=\"infor-all-product-item\">\n" +
                        "                                    " + x.getNameProduct() + "\n" +
                        "                            </div>\n" +
                        "                            <div class=\"price-all-product-item\">\n" +
                        "                                    " + currencyVN.format(x.getGiaBan()) + "\n" +
                        "                            </div>\n" +
                        "                        </a>\n" +
                        "                    </div>");
            } else {
                out.println("<div class=\"hover-all-product\">\n" +
                        "                        <a class=\"all-product-item\" href=\"" + request.getContextPath() + "/Product?id=" + x.getMaLapTop() + "\">\n" +
                        "                            <div class=\"img-all-product-item\"\n" +
                        "                                 style=\"background-image: url('" + x.getImage() + "')\">\n" +
                        "                            </div>\n" +
                        "                            <div class=\"infor-all-product-item\">\n" +
                        "                                    " + x.getNameProduct() + "\n" +
                        "                            </div>\n" +
                        "                            <div class=\"price-all-product-item\">\n" +
                        "                                    " + currencyVN.format(x.getGiaBan()) + "\n" +
                        "                            </div>\n" +
                        "                        </a>\n" +
                        "                    </div>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
