package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.UserServices;
import org.apache.commons.codec.digest.DigestUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@WebServlet(urlPatterns = {"/FindAccount", "/CheckOTP", "/UpdatePass", "/SendOTP"})
public class ForgotController extends HttpServlet {
    String emailStatic = "";

    private boolean checkListAccount(List accountList) {
        if (accountList.isEmpty() || accountList.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
        response.setHeader("Access-Control-Allow-Origin", "*");
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        switch (URL) {
            case "/FindAccount":
                emailStatic = request.getParameter("email");
                String base = request.getServletContext().getContextPath();
                if (checkListAccount(UserServices.getInstance().getAccount(emailStatic))) {
                    Random rand = new Random();
                    int otp = 100000 + rand.nextInt(900000);
                    sendEmail(otp);
                    UserServices.getInstance().updateOTP(emailStatic, otp);
                    request.setAttribute("openOTP", "");
                    request.getRequestDispatcher("").forward(request, response);
                } else {
                    request.setAttribute("errorFindAccount", "Email đăng kí không tồn tại");
                    request.setAttribute("email", emailStatic);
                    request.getRequestDispatcher("").forward(request, response);
                }
                break;
            case "/CheckOTP":
                int otp = Integer.parseInt(request.getParameter("otp"));
                if (checkListAccount(UserServices.getInstance().checkOTP(emailStatic, otp))) {
                    request.setAttribute("openNewPass", "");
                    request.getRequestDispatcher("").forward(request, response);
                } else {
                    request.setAttribute("openOTP", "Mã OTP đã nhập không hợp lệ");
                    request.getRequestDispatcher("").forward(request, response);
                }
                break;
        }
    }

    private void sendEmail(int otp) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "19130215@st.hcmuaf.edu.vn";
                String password = "0982625202javan3";
                return new PasswordAuthentication(username, password);
            }
        });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailStatic));
            message.setRecipients(Message.RecipientType.TO, emailStatic);
            message.setSubject("Mã kích hoạt", "utf-8");
            message.setText(String.valueOf(otp), "utf-8", "html");
            message.setReplyTo(message.getFrom());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"), request.getRequestURI().length());
        String base = request.getServletContext().getContextPath();
        switch (URL) {
            case "/UpdatePass":
                String newPass1 = request.getParameter("newPass1");
                String newPass2 = request.getParameter("newPass2");
                if (newPass1.equals(newPass2)) {
                    String md5Hex = DigestUtils.md5Hex(newPass1).toUpperCase();
                    UserServices.getInstance().updatePass(emailStatic, md5Hex);
                    response.sendRedirect(base);
                } else {
                    request.setAttribute("openNewPass", "Mật khẩu đã nhập không khớp");
                    request.getRequestDispatcher("").forward(request, response);
                }
                break;
        }
    }
}
