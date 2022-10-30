package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.ProductWithStatus;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "Pagination", value = "/Pagination")
public class Pagination extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String idHang = (String) session.getAttribute("idHang");
        List<ProductWithStatus> listProduct = null;
        Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
//        String orderBy = (String) session.getAttribute("orderBy");
        String orderBy = request.getParameter("value");
        session.setAttribute("orderBy", orderBy);
        int page = 1;
        if (request.getParameter("page") != null && request.getParameter("page") != "") {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
        int limit = 24;
        if (map == null) {
            if (idHang != null && idHang != "") {
                listProduct = ProductService.getInstance().getProductManufacturer(idHang, orderBy, limit, page);
            } else {
                listProduct = ProductService.getInstance().getAllProduct(orderBy,limit, page);
            }
        } else if (idHang == null) {
            listProduct = ProductService.getInstance().sortProduct(map, orderBy, limit, page);
        } else {
            listProduct = ProductService.getInstance().sortProductWithProducer(map, idHang, orderBy, limit, page);
        }

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        PrintWriter out = response.getWriter();
        for (ProductWithStatus x : listProduct) {
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
        doGet(request, response);
    }
}
