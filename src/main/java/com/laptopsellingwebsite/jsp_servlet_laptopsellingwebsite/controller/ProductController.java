package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CartDAO;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.CartServices;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/addProductToCart", "/Product","/addToCartNow"})

public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        switch (URL) {
            case "/Product":
                String id = request.getParameter("id");
                String url = request.getServletPath();
                HttpSession session = request.getSession();

                request.setAttribute("url", url);
                Multimap<String, String> map = (Multimap<String, String>) session.getAttribute("map");
                if (map != null) {
                    map.clear();
                }
                request.setAttribute("productID", ProductService.getInstance().getProductWithID(id));
                request.setAttribute("productsProductBS", ProductService.getInstance().getTopProductBestSeller(10));
                CartDAO cartDAO = new CartDAO();
                Account currentAccount = (Account) session.getAttribute("account");
                if (currentAccount != null) {
                    int idAccount = currentAccount.getId();
                    ArrayList<CartInfo> listProductOnHoverCart = cartDAO.getProductList(cartDAO.getProductIDFromCartByUserID(idAccount), cartDAO.getCurrentCartByUserID(idAccount));
                    int cost = cartDAO.totalCost(idAccount);
                    request.setAttribute("listProduct", listProductOnHoverCart);
                    int countProduct = 0;
                    for (CartInfo x: listProductOnHoverCart) {
                        countProduct += x.getSoluong();
                    }
                    request.setAttribute("totalProductHover", countProduct);
                    request.setAttribute("cost", cost);
                }
                request.getRequestDispatcher("jsp/product-page.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());



        String productIDForAdd = request.getParameter("id");
        CartDAO cartDAO = new CartDAO();
        int getProductRemainQuantity = cartDAO.getRemainNumber(productIDForAdd);
        int productQuantityWillAdd = cartDAO.getExportNumber(productIDForAdd) + 1;
        int getProductExportQuantity = cartDAO.getExportNumber(productIDForAdd);
        int getProductImportQuantity = cartDAO.getImportNumber(productIDForAdd);


        switch (URL) {
            case "/addProductToCart":
                HttpSession session = request.getSession();
                Account currentAccountForAdd = (Account) session.getAttribute("account");

                //kiem tra trang thai dang nhap
                String resultForAdd = "";
                if (currentAccountForAdd == null) {
                    resultForAdd = "0";
                    System.out.println("Chua dang nhap");
                    PrintWriter out = response.getWriter();
                    out.println(resultForAdd);
                } else {
                    int userIDForAdd = currentAccountForAdd.getId();
                    // xu ly truong hop so luong nhap nhieu hon ton kho
                    if (productQuantityWillAdd <= getProductImportQuantity) {
                        if (cartDAO.getCurrentCartByUserID(userIDForAdd) == "" || cartDAO.isPuschased(cartDAO.getCurrentCartByUserID(userIDForAdd)) == true) {
                            System.out.println("chua co gio hang");
                            cartDAO.insertCart(userIDForAdd);
                            cartDAO.insertProductToCart(productIDForAdd, userIDForAdd, 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                            resultForAdd = "1";
                            PrintWriter out = response.getWriter();
                            out.println(resultForAdd);
                        } else if (cartDAO.isProductOnCart(productIDForAdd, userIDForAdd) == false) {
                            System.out.println("da co gio hang, chua ton tai sp se them");
                            cartDAO.insertProductToCart(productIDForAdd, userIDForAdd, 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                            resultForAdd = "1";
                            PrintWriter out = response.getWriter();
                            out.println(resultForAdd);
                        } else {
                            System.out.println("da co gio hang, ton tai sp se them");
                            resultForAdd = "1";
                            PrintWriter out = response.getWriter();
                            out.println(resultForAdd);
                            cartDAO.updateProductQuantityByProductID(
                                    productIDForAdd,
                                    userIDForAdd,
                                    cartDAO.getProductQuantity(productIDForAdd, userIDForAdd) + 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                        }
                    } else {
                        System.out.println("Khong the them");
                        resultForAdd = "2";
                        PrintWriter out = response.getWriter();
                        out.println(resultForAdd);
                    }
                }
                break;

            case "/addToCartNow":
                HttpSession session1 = request.getSession();
                Account AccountForAddNow = (Account) session1.getAttribute("account");
                String resultForAddNow = "";
                //kiem tra trang thai dang nhap
                //neu chua dang nhap, gui loi ve client
                if (AccountForAddNow == null) {
                    resultForAddNow = "0";
                    System.out.println("Chua dang nhap");
                    PrintWriter out = response.getWriter();
                    out.println(resultForAddNow);
                }
                //Login success
                else {
                    System.out.println("Login success");
                    int currentAccountForAddNow = AccountForAddNow.getId();
                    // xu ly truong hop so luong nhap nhieu hon ton kho
                    if (productQuantityWillAdd <= getProductImportQuantity) {
                        //truong hop gio hang chua ton tai
                        if (cartDAO.getCurrentCartByUserID(currentAccountForAddNow) == "" || cartDAO.isPuschased(cartDAO.getCurrentCartByUserID(currentAccountForAddNow)) == true) {
                            resultForAddNow = "1";
                            cartDAO.insertCart(currentAccountForAddNow);
                            cartDAO.insertProductToCart(productIDForAdd, currentAccountForAddNow, 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                        }
                        //truong hop gio hang chua co san pham chuan bi them vao
                        else if (cartDAO.isProductOnCart(productIDForAdd, currentAccountForAddNow) == false) {
                            cartDAO.insertProductToCart(productIDForAdd, currentAccountForAddNow, 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                            resultForAddNow = "1";
//                            PrintWriter out = response.getWriter();
//                            out.println(resultForAddNow);
//                            System.out.println("Them vao gio hang da ton tai, chua co san pham sap them vao gio" );


                        } else {
                            // truong hop gio hang da ton tai san pham san pham
                            resultForAddNow = "1";
//                            PrintWriter out = response.getWriter();
//                            out.println(resultForAddNow);
//                            System.out.println("Them vao gio hang da ton tai, c san pham sap them  da co trong gio");
////                          request.getRequestDispatcher("jsp/cart.jsp").forward(request,response);
                            cartDAO.updateProductQuantityByProductID(
                                    productIDForAdd,
                                    currentAccountForAddNow,
                                    cartDAO.getProductQuantity(productIDForAdd, currentAccountForAddNow) + 1);
                            cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);

                        }
                        //chuyen huong sang trang cart khi thuc hien
                        PrintWriter out = response.getWriter();
                        out.println(resultForAddNow);
                    } // truong hop so luong san pham khong con trong kho
                    else {
                        resultForAddNow = "2";
                        PrintWriter out = response.getWriter();
                        out.println(resultForAddNow);
                    }
                }
                break;

        }

    }
}