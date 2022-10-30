package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CartDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AboutUsController", value = "/AboutUs")
public class AboutUsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
        if (map != null){
            map.clear();
        }
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
        request.getRequestDispatcher("jsp/about-us.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
