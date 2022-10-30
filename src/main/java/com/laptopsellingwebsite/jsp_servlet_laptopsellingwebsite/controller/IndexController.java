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

@WebServlet(name = "IndexController", value = "/Index")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
        if (map != null) {
            map.clear();
        }
        request.setAttribute("productsTop4", ProductService.getInstance().getAllProducer());
        request.setAttribute("productsTop", ProductService.getInstance().getTopProduct1(4));
        request.setAttribute("productsProductBS", ProductService.getInstance().getTopProductBestSeller(10));
        request.setAttribute("topProducer", ProductService.getInstance().getTopProducer(4));
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
