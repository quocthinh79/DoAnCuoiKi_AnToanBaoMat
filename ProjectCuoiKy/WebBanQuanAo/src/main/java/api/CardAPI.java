package api;

import Beans.Account;
import Dao.CartDao;
import Services.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Card", value = "/api/card")
public class CardAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int countCardItem = 0;
        if(account!=null) {
            int idCart = account.getUserID();
            countCardItem = CartDao.countCartItem(idCart);
        }
        PrintWriter pw = response.getWriter();
        pw.println(countCardItem);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        Account account = (Account) request.getSession().getAttribute("account");
        Integer idCart = null;
        idCart = account.getUserID();
        String idProduct = request.getParameter("productId");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        CartService.addToCart(idCart, idProduct, quantity, color, size);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCard = Integer.parseInt(request.getParameter("idCard"));
        String idProduct = request.getParameter("idProduct");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        CartDao.deleteItem(idCard, idProduct, size, color);
    }
}