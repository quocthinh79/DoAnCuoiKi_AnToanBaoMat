package controller;

import cipher.DSA;
import cipher.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "myAccountController", value = "/my-account")
public class MyAccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "Tài khoản");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/myAccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        String action = request.getParameter("action") + "";
        switch (action) {
            case "verify":
                String privateKey = request.getParameter("privateKey");
                String txtPDF = request.getParameter("pdf");

//              Set private key cho DSA
                DSA dsa = new DSA();
                try {
                    dsa.setPrivateKeyFromText(privateKey);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
                    pw.println(-1);
                    pw.flush();
                    e.printStackTrace();
                }

//              Hashing nội dung pdf bằng MD5
                String hashing = MD5.MD5Text(txtPDF);
                byte[] dataByte = hashing.getBytes(StandardCharsets.UTF_8);
                byte[] signature;
                try {
                    signature = DSA.createDigitalSignature(dataByte, dsa.getPrivateKey());
                } catch (Exception e) {
                    pw.println(-1);
                    pw.flush();
                    e.printStackTrace();
                }


                break;
            default:
                System.out.println("khong tim thay action nao");
                break;
        }
    }
}
