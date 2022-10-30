package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Account;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.CartInfo;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.CartDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/Cart","/clearAllProduct","/addQuantity","/subtractQuantity","/clearAProduct","/buy","/cart-hover"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        HttpSession session = request.getSession();

        Account currentAccount = (Account) session.getAttribute("account");
        if (currentAccount == null) {
            request.setAttribute("currentStatus", 0);
            request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
        } else {
            request.setAttribute("currentStatus", 1);
            int id = currentAccount.getId();
            CartDAO cartDAO = new CartDAO();

            switch(URL) {
                case "/Cart":
                    if(cartDAO.isPuschased(cartDAO.getCurrentCartByUserID(id)) == false) {
                        ArrayList<CartInfo> listProductOnCart = cartDAO.getProductList(cartDAO.getProductIDFromCartByUserID(id), cartDAO.getCurrentCartByUserID(id));
                        int totalCost = cartDAO.totalCost(id);
                        request.setAttribute("listProduct", listProductOnCart);
                        request.setAttribute("totalCost", totalCost);
                    }
                    ArrayList<CartInfo> listProductOnHoverCart = cartDAO.getProductList(cartDAO.getProductIDFromCartByUserID(id), cartDAO.getCurrentCartByUserID(id));
                    int cost = cartDAO.totalCost(id);
                    request.setAttribute("listProduct", listProductOnHoverCart);
                    int countProduct = 0;
                    for (CartInfo x: listProductOnHoverCart) {
                        countProduct += x.getSoluong();
                    }
                    request.setAttribute("totalProductHover", countProduct);
                    request.setAttribute("cost", cost);
                    request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
                    break;


                case "/addQuantity":
                    String productIDForAdd = request.getParameter("id");
                    int getProductRemainQuantity = cartDAO.getRemainNumber(productIDForAdd);
                    int productQuantityWillAdd = cartDAO.getExportNumber(productIDForAdd)+ 1;
                    int getProductExportQuantity = cartDAO.getExportNumber(productIDForAdd);
                    int getProductImportQuantity = cartDAO.getImportNumber(productIDForAdd);
                    String result ;
// xu ly truong hop so luong nhap nhieu hon ton kho
                    if (productQuantityWillAdd <= getProductImportQuantity) {
                        result = "1";
//                    request.setAttribute("result",result);
                        PrintWriter out = response.getWriter();
                        out.println(result);
                        System.out.println("1");
                        cartDAO.updateProductQuantityByProductID(
                                productIDForAdd,
                                id,
                                cartDAO.getProductQuantity(productIDForAdd, id) + 1);
                        cartDAO.updateWarehouse(productIDForAdd, getProductExportQuantity + 1, getProductRemainQuantity - 1);
                    } else {
                        result = "0";
//                    request.setAttribute("result",result);
                        PrintWriter out = response.getWriter();
                        out.println(result);
                        System.out.println("0");
                    }
                    break;


                case "/subtractQuantity":

                    String productIDForSubtract = request.getParameter("id");
                    int getProductRemainQuantityForSubtract = cartDAO.getRemainNumber(productIDForSubtract);
                    int productQuantityWillSubtract = cartDAO.getProductQuantity(productIDForSubtract, id) - 1;
                    int getProductExportQuantityForSubtract = cartDAO.getExportNumber(productIDForSubtract);
                    int getProductImportQuantityForSubtract = cartDAO.getImportNumber(productIDForSubtract);
                    if(getProductExportQuantityForSubtract > 1) {
                        cartDAO.updateProductQuantityByProductID(
                                productIDForSubtract,
                                id,
                                productQuantityWillSubtract);

                        cartDAO.updateWarehouse(productIDForSubtract, productQuantityWillSubtract, getProductImportQuantityForSubtract - productQuantityWillSubtract);
                    }

                    break;

                case "/clearAProduct":
                    String productIDForDelete = request.getParameter("idForDelete");
                    int getProductQuantityForDelete = cartDAO.getProductQuantity(productIDForDelete,id);
                    int getProductExportQuantityForDelete = cartDAO.getExportNumber(productIDForDelete);
                    int getProductRemainQuantityForDelete = cartDAO.getRemainNumber(productIDForDelete);

                    cartDAO.updateWarehouse(productIDForDelete,getProductExportQuantityForDelete - getProductQuantityForDelete,getProductRemainQuantityForDelete + getProductQuantityForDelete);
                    cartDAO.removeProductFromCart(productIDForDelete, id);

                    System.out.println(cartDAO.removeProductFromCart(productIDForDelete, id));
                    break;

                case "/clearAllProduct":
                    ArrayList<String> listProductForClear = cartDAO.getProductIDFromCartByUserID(id);
                    PrintWriter clearOut = response.getWriter();
                    if(listProductForClear.size() == 0 ) {
                        clearOut.println("Cart Empty");
                    } else {
                        for(String productIdForClear:listProductForClear) {
                            int getProductQuantityForClear = cartDAO.getProductQuantity(productIdForClear,id);
                            int getProductExportQuantityForClear = cartDAO.getExportNumber(productIdForClear);
                            int getProductRemainQuantityForClear = cartDAO.getRemainNumber(productIdForClear);
                            cartDAO.updateWarehouse(productIdForClear,getProductExportQuantityForClear - getProductQuantityForClear,getProductRemainQuantityForClear + getProductQuantityForClear);
                        }
                        cartDAO.clearCart(id);
                        clearOut.println(1);
                    }
                    break;
                case "/buy":
                    ArrayList<String> listProduct = cartDAO.getProductIDFromCartByUserID(id);
                    PrintWriter out = response.getWriter();

                    if(listProduct.size() > 0) {
                        int totalCostForBuy = cartDAO.totalCost(id);
                        cartDAO.updateCart(id, totalCostForBuy);
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                    break;


            }
        }

    }
}