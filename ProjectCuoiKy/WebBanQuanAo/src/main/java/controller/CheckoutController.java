package controller;

import Beans.Account;
import Beans.CartItem;
import Beans.OrderInfor;
import Dao.AccountDao;
import Dao.CartDao;
import Dao.PaymentDao;
import Services.CartService;
import Services.SendMailService;
import cipher.DSA;
import cipher.MD5;
import writetopdf.WriteDataToPdf;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
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
        String privateKey = request.getParameter("privateKey");

        String userAccount = request.getParameter("userAccount");
        String idCart = request.getParameter("idCart");
        String dia_chi = request.getParameter("dia_chi");
        String sdt = request.getParameter("sdt");
        String nguoi_nhan = request.getParameter("nguoi_nhan");
        double thanh_tien = 0;

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null){
            System.out.println("account null");
            return;
        }

        List<CartItem> cartItemList = CartService.getCartItems(Integer.parseInt(idCart));
        for (CartItem item : cartItemList) {
            thanh_tien += item.getPrice();
        }

//        OrderInfor orderInfor = new OrderInfor(Integer.parseInt(idCart), cartItemList, thanh_tien, nguoi_nhan, sdt, dia_chi);

        DSA dsa = new DSA();
        try {
            dsa.setPrivateKeyFromText(privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            e.printStackTrace();
        }


        int ma_hoa_don = PaymentDao.createNewPayment(Integer.parseInt(idCart), dia_chi, sdt, nguoi_nhan, thanh_tien);
        if (ma_hoa_don > 0) {
            for (CartItem item : cartItemList) {
                PaymentDao.addPaymentDetail(ma_hoa_don, item.getIdProduct(), item.getSize(), item.getColor(), item.getQuantity());
                CartDao.deleteItem(Integer.parseInt(idCart), item.getIdProduct(), item.getSize(), item.getColor());
            }
//            String hostName = request.getHeader("host")+request.getContextPath();
            String realPath = request.getServletContext().getRealPath("/assets/CompletePDF.pdf");
            System.out.println(" realPath : " + realPath);

            System.out.println("in write order");
            OrderInfor orderInfor = new OrderInfor(Integer.parseInt(idCart),cartItemList,thanh_tien,nguoi_nhan,sdt,dia_chi);
            WriteDataToPdf.getInstance().writeObjectToPdf(orderInfor,realPath);
            System.out.println("after write" + cartItemList.size());

            System.out.println(account.getEmail()+ " - " +realPath);
            boolean s = SendMailService.sendMailwithFile(account.getEmail(),"hoa don ","test send with",realPath);
            System.out.println(s);

        }
    }

    public byte[] digitalSignature(OrderInfor orderInfor, String privateKey, DSA dsa) throws Exception {

        return null;
    }

    public static boolean verifyDigitalSignature(OrderInfor orderInfor, byte[] sign, DSA dsa, String publicKey) throws Exception {

        return true;
    }
}
