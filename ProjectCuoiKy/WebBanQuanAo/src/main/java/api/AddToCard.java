package api;

import Beans.Account;
import Beans.Product;
import Dao.ProductDao;
import Services.CartService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AddToCard", value = "/api/add-to-card")
public class AddToCard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = ProductDao.loadProduct();
        Gson gson = new Gson();
        String productsJson = gson.toJson(products);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(productsJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("productID-noUser"));

        Cookie[] cookies = request.getCookies();
        Account account = (Account) request.getSession().getAttribute("account");
        Integer idCart = null;
        idCart = account.getUserID();
        String idProduct = request.getParameter("productId");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartService.addToCart(idCart, idProduct, quantity, color, size);

//        if (idCart != null) {
//            CartService.addToCart(idCart, idProduct, quantity, color, size);
//        } else {
//            Cookie cookieProductID = new Cookie("productID", idProduct);
//            Cookie cookieQuantity = new Cookie("quantity", quantity + "");
//            Cookie cookieColor = new Cookie("color", color);
//            Cookie cookieSize = new Cookie("size", size);
//
//            cookieProductID.setMaxAge(60 * 60 * 24);
//            response.addCookie(cookieProductID);
//            cookieQuantity.setMaxAge(60 * 60 * 24);
//            response.addCookie(cookieQuantity);
//            cookieColor.setMaxAge(60 * 60 * 24);
//            response.addCookie(cookieColor);
//            cookieSize.setMaxAge(60 * 60 * 24);
//            response.addCookie(cookieSize);
//        }
    }
}
