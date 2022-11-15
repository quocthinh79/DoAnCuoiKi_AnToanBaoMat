package controller;

import Beans.Account;
import Beans.CartItem;
import Dao.CartDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "shoppingCartController", value = "/shopping-cart")
public class ShoppingCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idCart = -1;
        Account ac =  (Account) request.getSession().getAttribute("account");
        idCart = ac.getUserID();
        List<CartItem> cartItems = CartDao.getCartItems(idCart);
        request.setAttribute("idCard", idCart);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("pageName", "Giỏ hàng");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shoppingCart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
