package controller.admin;

import Dao.AccountDao;
import Services.AccountServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "userManageController", value = "/admin-user-manage")
public class UserManageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account == null || !(account.getRole().equals("ADMIN"))) {
//            response.sendRedirect("login");
//        } else {
        String base = request.getServletContext().getContextPath();
        request.setAttribute("base", base);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user-manage.jsp");
        request.setAttribute("subTabName", "manageUser");
        request.setAttribute("tabName", "manage");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String idAccount = request.getParameter("idAccount");
        String lastName = request.getParameter("last-name");
        String firstName = request.getParameter("first-name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role_edit");

        switch (action) {
            case "update":
                boolean update = AccountServices.updateAccount(idAccount, lastName, firstName, phone, email, address, userName, role);
                System.out.println("Da update: " + update);
                break;
            default: {

                int checkAddAccount = AccountServices.addNewAccount(lastName, firstName, phone, email, userName, password);
                String message = "Th??m th??nh c??ng";
                switch (checkAddAccount) {
                    case 0:
                        message = "Th??m t??i kho???n th??nh c??ng";
                        break;
                    case 1:
                        message = "???? x???y ra l???i trong qu?? tr??nh ????ng k?? vui l??ng th??? l???i sau";
                        break;
                    case 2:
                        message = "Email ???? ???????c s??? d???ng";
                        break;
                    default:
                        message = "T??n t??i kho???n ???? ???????c s??? d???ng, Vui l??ng ch???n t??n t??i kho???n kh??c";
                        break;
                }
                request.setAttribute("message", message);
                break;
            }

        }

        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
