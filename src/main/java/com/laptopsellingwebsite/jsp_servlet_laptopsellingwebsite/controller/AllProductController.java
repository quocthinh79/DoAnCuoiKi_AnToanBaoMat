package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CartDAO;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AllProductController", value = "/AllProduct")
public class AllProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idHang = request.getParameter("idHang");
        HttpSession session = request.getSession();
        session.setAttribute("idHang", idHang);
        Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
        if (map != null) {
            map.clear();
        }
        int page = 1;
        if (request.getParameter("page") != null && request.getParameter("page") != "") {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
        int limit = 24;


        if (idHang != null) {
            int totalPageProducer = ProductService.getInstance().getTotalPageByProducer(idHang);
            int totalProducer = (int) Math.ceil((double) totalPageProducer / (double) limit);
            request.setAttribute("totalPage", totalProducer);
            request.setAttribute("allProduct", ProductService.getInstance().getProductManufacturer(idHang, limit, page));
            request.setAttribute("allProducer", ProductService.getInstance().getProducerWithID(idHang));
            request.setAttribute("filter", ProductService.getInstance().getProductManufacturer(idHang));
        } else {
            int totalPage = ProductService.getInstance().getTotalPage();
            int total = (int) Math.ceil((double) totalPage / (double) limit);
            request.setAttribute("totalPage", total);
            request.setAttribute("allProduct", ProductService.getInstance().getAllProductWithStatus(limit, page));
            request.setAttribute("allProducer", ProductService.getInstance().getAllProducer());
            request.setAttribute("filter", ProductService.getInstance().getAllProduct());
        }

        session.removeAttribute("lowestPrice");
        session.removeAttribute("highPrice");
        CartDAO cartDAO = new CartDAO();
        Account currentAccount = (Account) session.getAttribute("account");
        if (currentAccount != null) {
            int id = currentAccount.getId();
            ArrayList<CartInfo> listProductOnHoverCart = cartDAO.getProductList(cartDAO.getProductIDFromCartByUserID(id), cartDAO.getCurrentCartByUserID(id));
            int cost = cartDAO.totalCost(id);
            request.setAttribute("listProduct", listProductOnHoverCart);
            int countProduct = 0;
            for (CartInfo x: listProductOnHoverCart) {
                countProduct += x.getSoluong();
            }
            request.setAttribute("totalProductHover", countProduct);
            request.setAttribute("cost", cost);
        }
        request.getRequestDispatcher("jsp/all-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}