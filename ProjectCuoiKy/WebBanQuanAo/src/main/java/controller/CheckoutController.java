package controller;

import Beans.Account;
import Beans.CartItem;
import Dao.CartDao;
import Dao.PaymentDao;
import Services.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "checkoutController", value = "/checkout")
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Account ac = (Account) request.getSession().getAttribute("account");
        if (ac != null) {
            int idCart = ac.getUserID();
            request.setAttribute("idCart", idCart);
            List<CartItem> cartItems = CartDao.getCartItems(idCart);
            int totalPrice = 0;
            for (CartItem item : cartItems) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalPrice", totalPrice);
        }
        request.setAttribute("pageName", "Thanh toán");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/checkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String idCart = request.getParameter("idCart");
        String dia_chi = request.getParameter("dia_chi");
        String sdt = request.getParameter("sdt");
        String nguoi_nhan = request.getParameter("nguoi_nhan");
        double thanh_tien = 0;

        List<CartItem> cartItemList = CartService.getCartItems(Integer.parseInt(idCart));
        for (CartItem item : cartItemList) {
            thanh_tien += item.getPrice();
        }

        int ma_hoa_don = PaymentDao.createNewPayment(Integer.parseInt(idCart), dia_chi, sdt, nguoi_nhan, thanh_tien);
        if (ma_hoa_don > 0) {
            for (CartItem item : cartItemList) {
                PaymentDao.addPaymentDetail(ma_hoa_don, item.getIdProduct(), item.getSize(), item.getColor(), item.getQuantity());
                CartDao.deleteItem(Integer.parseInt(idCart), item.getIdProduct(), item.getSize(), item.getColor());
            }
        }
    }
}
