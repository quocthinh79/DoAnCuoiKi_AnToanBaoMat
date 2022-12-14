package controller;

import Beans.Account;
import Beans.CartItem;
import Beans.OrderInfor;
import Dao.AccountDao;
import Dao.CartDao;
import Dao.PaymentDao;
import Dao.VerifyDao;
import Services.CartService;
import Services.SendMailService;
import cipher.DSA;
import cipher.MD5;
import signature_digital.DigitallySignPDF;
import writetopdf.WriteDataToPdf;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
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
        PrintWriter pw = response.getWriter();

        String userAccount = request.getParameter("userAccount");
        String idCart = request.getParameter("idCart");
        String dia_chi = request.getParameter("dia_chi");
        String sdt = request.getParameter("sdt");
        String nguoi_nhan = request.getParameter("nguoi_nhan");
        double thanh_tien = 0;

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
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
            pw.println(-1);
            pw.flush();
            e.printStackTrace();
        }


        int ma_hoa_don = PaymentDao.createNewPayment(Integer.parseInt(idCart), dia_chi, sdt, nguoi_nhan, thanh_tien);
        try {
            if (ma_hoa_don > 0) {
                for (CartItem item : cartItemList) {
                    PaymentDao.addPaymentDetail(ma_hoa_don, item.getIdProduct(), item.getSize(), item.getColor(), item.getQuantity());
                    CartDao.deleteItem(Integer.parseInt(idCart), item.getIdProduct(), item.getSize(), item.getColor());
                }
//            String hostName = request.getHeader("host")+request.getContextPath();
                String realPath = request.getServletContext().getRealPath("/assets/CompletePDF.pdf");

                OrderInfor orderInfor = new OrderInfor(Integer.parseInt(idCart), cartItemList, thanh_tien, nguoi_nhan, sdt, dia_chi);
                String dataString = MD5.MD5Text(orderInfor.toString());
                byte[] dataByte = dataString.getBytes(StandardCharsets.UTF_8);
                try {
                    dsa.setPrivateKeyFromText(privateKey);
                } catch (Exception e) {
                    pw.println(-1);
                    pw.flush();
                    e.printStackTrace();
                }
                byte[] signature = DSA.createDigitalSignature(dataByte, dsa.getPrivateKey());
                String publicKey = AccountDao.getPublicKeyByUser(userAccount);
                try {
                    dsa.setPublicKeyFromText(publicKey);
                } catch (Exception e) {
                    pw.println(-1);
                    pw.flush();
                    e.printStackTrace();
                }
                if (DSA.verifyDigitalSignature(dataByte, signature, dsa.getPublicKey())) {
                    WriteDataToPdf.getInstance().writeObjectToPdf(orderInfor, realPath);
                    pw.println(1);
                    pw.flush();
                    String realPfx = request.getServletContext().getRealPath("/assets/certificate.pfx");
                    DigitallySignPDF.signToPdf(realPfx, realPath, orderInfor);

                    String hashingcode = MD5.MD5File(new File(realPath));
                    VerifyDao.addHashing(userAccount,orderInfor.getId(),hashingcode);
                } else {
                    pw.println(-1);
                    pw.flush();
                }
                System.out.println("list item:" + cartItemList.size());

                boolean s = SendMailService.sendMailwithFile(account.getEmail(), "hoa don ", "Day la hoa don cua ban \n" +
                        "cam on ban da trai nghiem mua sam tai shop cua chung toi", realPath);
                System.out.println(s);
            }
        } catch (Exception e) {
            pw.println(-1);
            pw.flush();
            e.printStackTrace();

        }
    }

    public byte[] digitalSignature(OrderInfor orderInfor, String privateKey, DSA dsa) throws Exception {

        return null;
    }

    public static boolean verifyDigitalSignature(OrderInfor orderInfor, byte[] sign, DSA dsa, String publicKey) throws Exception {

        return true;
    }
}
