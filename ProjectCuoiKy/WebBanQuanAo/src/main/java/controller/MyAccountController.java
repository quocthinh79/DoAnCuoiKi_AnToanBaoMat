package controller;

import Beans.Account;
import Dao.AccountDao;
import Dao.VerifyDao;
import cipher.DSA;
import cipher.MD5;
import cipher.ReadAndWriteFile;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "myAccountController", value = "/my-account")
public class MyAccountController extends HttpServlet {
    private ServletFileUpload uploader = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "Tài khoản");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/myAccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/notification.jsp");
        String message = "";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        System.out.println("in post controller");
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Context is not multipart");
            message = "Context is not multipart";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
            throw new ServletException("Context is not multipart");
        }

        System.out.println("save file");
// upload hóa đơn lên server
        List<String> dirlist = new ArrayList<>();
        try {
            List<FileItem> fileItemList = uploader.parseRequest(request);

            File dir = null;
            Map<String, String> map = new HashMap<String, String>();
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) {
                    map.put(fileItem.getFieldName(), fileItem.getString("UTF-8").trim());
                } else if (!fileItem.isFormField()) {
                    dir = new File(request.getServletContext().getAttribute("FILE_DIR") + File.separator + fileItem.getName());
                    if (dir.exists())
                        dir = new File(request.getServletContext().getAttribute("FILE_DIR") + File.separator + System.currentTimeMillis() + "-" + fileItem.getName());
//                    System.out.println(count + "path file " + dir.getAbsolutePath());
//                    System.out.println("http://localhost:8080/WebBanQuanAo/" + request.getServletContext().getAttribute("storePath") + File.separator + fileItem.getName());
                    fileItem.write(dir);
                    dirlist.add(dir.getAbsolutePath());
                }
            }
        } catch (FileUploadException e) {
//            System.out.println("fileupload exception");
            message = "File upload Error";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
        } catch (Exception e) {
            message = "Runtime out";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
            throw new RuntimeException(e);
        }

        String privateKey = ReadAndWriteFile.readKeyFromFile(dirlist.get(1));
        String PDFpath = dirlist.get(0);
        HttpSession session = request.getSession();
        Account account1 = (Account) session.getAttribute("account");
        String userAccount = account1.getUserName();
        String txtPDF = "";
        try {
            // lấy nội dung từ hóa đơn
            PdfReader pdfReader = new PdfReader(PDFpath);
            txtPDF = PdfTextExtractor.getTextFromPage(pdfReader, 1);
            pdfReader.close();
        } catch (Exception e) {
            message = "Xảy ra lỗi trong quá trình đọc file PDF! Có thể file PDF bạn upload không phải là hóa đơn chúng tôi cung cấp";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
        }
        String[] lines = null;
        if (!txtPDF.equals("")) {
            lines = txtPDF.split("\n");
        }

//              Set private key cho DSA
        DSA dsa = new DSA();
        try {
            dsa.setPrivateKeyFromText(privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            message = "Xảy ra lỗi trong quá trình xác nhận hóa đơn! Vui lòng kiểm tra lại";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
        }

//              Hashing nội dung pdf bằng MD5
        String hashing = MD5.MD5File(new File(PDFpath));
        byte[] dataByte = hashing.getBytes(StandardCharsets.UTF_8);
        byte[] signature;
        try {
            signature = DSA.createDigitalSignature(dataByte, dsa.getPrivateKey());
// dùng publickey của người dùng giải mã signature
            String publicKey = AccountDao.getPublicKeyByUser(userAccount);
            dsa.setPublicKeyFromText(publicKey);

// so sánh mã hash của hóa đơn do người dùng gửi lên với mã hash được lưu trữ trên database
            int orderId = Integer.parseInt(lines[1].substring(15));
            String dbhashing = VerifyDao.findHashCode(userAccount, orderId);
            byte[] dataByte2 = dbhashing.getBytes(StandardCharsets.UTF_8);
            if (DSA.verifyDigitalSignature(dataByte2, signature, dsa.getPublicKey())) {
                message = "Đã xác thực hoá đơn thành công";
                request.setAttribute("message", message);
                request.setAttribute("pageName", "Thông báo");
                rd.forward(request, response);
            } else {
                message = "Xác thực không thành công, hoá đơn hoặc key không hợp lệ!";
                request.setAttribute("message", message);
                request.setAttribute("pageName", "Thông báo");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            message = "Xảy ra lỗi trong quá trình xác nhận hóa đơn! Vui lòng kiểm tra lại";
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            rd.forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory filefactory = new DiskFileItemFactory();
        File filedir = (File) getServletContext().getAttribute("FILE_DIR_FILE");
        filefactory.setRepository(filedir);
        this.uploader = new ServletFileUpload(filefactory);
    }
}
