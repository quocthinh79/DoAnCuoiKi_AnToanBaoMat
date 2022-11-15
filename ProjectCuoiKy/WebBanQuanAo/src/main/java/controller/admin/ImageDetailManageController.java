package controller.admin;

import Services.ProductServices;
import Services.UploadService;
import api.object_reponse.InfoFormAddProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImageDetailManageController", value = "/admin-image-manage")
@MultipartConfig()
public class ImageDetailManageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account == null || !(account.getRole().equals("ADMIN"))) {
//            response.sendRedirect("login");
//        } else {
        InfoFormAddProduct infoFormAddProduct = ProductServices.getInfoFormAddProduct();
        String base = request.getServletContext().getContextPath();
        request.setAttribute("base", base);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/image-detail-manage.jsp");
        request.setAttribute("subTabName", "manageImageDetail");
        request.setAttribute("infoForm", infoFormAddProduct);
        request.setAttribute("tabName", "manage");

        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String colorProduct = request.getParameter("color-product");
        String action = request.getParameter("action");
        String message = "";
        switch (action) {
            case "add": {
                List<Part> detailImgs = (List<Part>) request.getParts();
//                String readPath = request.getServletContext().getRealPath("/assets/imgs/product-imgs");
                String readPath = request.getServletContext().getRealPath("/assets/uploads/");

                String listImagePath[] = new String[detailImgs.size() - 3];
                int count = 0;
                for (int i = 0; i < detailImgs.size(); i++) {
                    if (detailImgs.get(i).getName().equals("images")) {
                        listImagePath[count++] = UploadService.uploadFile(detailImgs.get(i), readPath);
                    }
                }
                boolean status = ProductServices.addImageDetail(id, colorProduct, listImagePath);
                message = status ? "Thêm hình ảnh thành công" : "Thêm hình ảnh thất bại, vui lòng kiểm tra lại";
                break;
            }
            case "delete": {
//                boolean status = ColorDao.deleteColor(idColor);
//                message = status ? "Xóa màu thành công" : "Xóa màu thất bại vui lòng kiểm tra lại";
                break;
            }
            case "edit": {
//                boolean status = ColorDao.editColor(idColor, colorName);
//                message = status ? "Chỉnh sửa màu thành công" : "Chỉnh sửa màu thất bại vui lòng kiểm tra lại";
                break;
            }
            default:
                break;
        }
        request.setAttribute("message", message);
        doGet(request, response);
    }
}
