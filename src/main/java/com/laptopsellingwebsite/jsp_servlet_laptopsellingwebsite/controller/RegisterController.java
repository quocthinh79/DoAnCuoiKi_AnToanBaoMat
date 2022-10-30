package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.AdminServices;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.UserServices;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "RegisterController", value = "/Register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hoTen = request.getParameter("hoTen");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String diaChi = request.getParameter("diaChi");
        String email = request.getParameter("email");
        String base = "https://" + request.getServerName();
        String md5Hex = DigestUtils.md5Hex(pass).toUpperCase();

        String urlAPI = "https://emailvalidation.abstractapi.com/v1/?api_key=e487b294b87a42f2a093a546613cf18c&email=" + email;
        HttpURLConnection conn=null;
        String responsse = "";
        try{
            URL url = new URL(urlAPI);
            conn = (HttpURLConnection)url.openConnection();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            } else {
                StringBuilder strBuf = new StringBuilder();
                BufferedReader reader=null;
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String output = null;
                while ((output = reader.readLine()) != null)
                    strBuf.append(output);
                JSONObject json = new JSONObject(String.valueOf(strBuf));
                responsse = json.getString("deliverability");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        if (responsse.equalsIgnoreCase("UNDELIVERABLE")) {
            request.setAttribute("existsUsername", "Email đăng kí không hợp lệ");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("username", username);
            request.setAttribute("pass", pass);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("").forward(request, response);
        } else if (!UserServices.checkUser(username) && !UserServices.getInstance().checkEmail(email) && responsse.equalsIgnoreCase("DELIVERABLE")) {
            UserServices.getInstance().registerCustomer(hoTen, username, md5Hex, diaChi, email);
            response.sendRedirect(base);
        } else {
            request.setAttribute("existsUsername", "Username hoặc email bạn đăng kí đã tồn tại");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("username", username);
            request.setAttribute("pass", pass);
            request.setAttribute("diaChi", diaChi);
            request.setAttribute("email", email);
            request.getRequestDispatcher("").forward(request, response);
        }
    }
}
