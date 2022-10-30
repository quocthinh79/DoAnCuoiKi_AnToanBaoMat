package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CartDAO;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.AdminServices;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.CustomerServices;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.UserServices;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(urlPatterns = {"/EditCustomer", "/Customer"})
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        switch (URL) {
            case "/Customer":
                HttpSession session = request.getSession();
                Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
                if (map != null) {
                    map.clear();
                }
                Account currentAccount = (Account) session.getAttribute("account");
                request.setAttribute("allAccount", CustomerServices.getInstance().getAccountCurrentCustomer(currentAccount.getId()));
                CartDAO cartDAO = new CartDAO();
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
                request.getRequestDispatcher("jsp/customer.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("send");
        String dataBefore = request.getParameter("contentBefore");
        String dataTemp = data;
        String[] exactlyData = dataTemp.split(",");
        String[] exactlyDataBefore = dataBefore.split(",");
        String currentTable = request.getParameter("currentTable");
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        PrintWriter out = response.getWriter();
        switch (URL) {
            case "/EditCustomer":
                String dieuKienUpdate = request.getParameter("dieuKien");
                switch (currentTable) {
                    case "Account":
                        String passBefore = exactlyDataBefore[3];
                        String hoTen = exactlyData[1];
                        String username = exactlyData[2];
                        String pass = exactlyData[3];
                        String md5Hex = null;
                        if (!passBefore.equalsIgnoreCase(pass)) {
                            md5Hex = DigestUtils.md5Hex(pass).toUpperCase();
                        } else {
                            md5Hex = pass;
                        }
                        String diaChi = exactlyData[4];
                        String email = exactlyData[5];
                        int dieuKien = Integer.parseInt(dieuKienUpdate);
                        String urlAPI = "https://emailvalidation.abstractapi.com/v1/?api_key=e487b294b87a42f2a093a546613cf18c&email=" + email;
                        HttpURLConnection conn = null;
                        String responsse = "";
                        try {
                            java.net.URL url = new URL(urlAPI);
                            conn = (HttpURLConnection) url.openConnection();

                            if (conn.getResponseCode() != 200) {
                                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                                        + conn.getResponseCode());
                            } else {
                                StringBuilder strBuf = new StringBuilder();
                                BufferedReader reader = null;
                                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                                String output = null;
                                while ((output = reader.readLine()) != null)
                                    strBuf.append(output);
                                JSONObject json = new JSONObject(String.valueOf(strBuf));
                                responsse = json.getString("deliverability");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (responsse.equalsIgnoreCase("UNDELIVERABLE")) {
                            out.println("Email not a available");
                        } else {
                            out.println(CustomerServices.getInstance().updateAccount(hoTen, username, md5Hex, diaChi, email, dieuKien));
                        }
                        break;
                }
                break;
        }
    }
}
