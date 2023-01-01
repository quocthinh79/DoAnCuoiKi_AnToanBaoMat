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
import signature_digital.DigitallySignPDF;
import writetopdf.WriteDataToPdf;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

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
        System.out.println("in post controller");
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Context is not multipart");
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
        } catch (Exception e) {
//            System.out.println("runtime out");
            throw new RuntimeException(e);
        }

        String privateKey = ReadAndWriteFile.readKeyFromFile(dirlist.get(1));
        String PDFpath = dirlist.get(0);
        HttpSession session = request.getSession();
        Account account1 = (Account) session.getAttribute("account");
        String userAccount = account1.getUserName();
// lấy nội dung từ hóa đơn
        PdfReader pdfReader = new PdfReader(PDFpath);
        String txtPDF = PdfTextExtractor.getTextFromPage(pdfReader, 1);
        String[] lines = txtPDF.split("\n");
        int orderId = Integer.parseInt(lines[1].substring(15));
        pdfReader.close();

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
        String hashing = MD5.MD5File(new File(PDFpath));
        byte[] dataByte = hashing.getBytes(StandardCharsets.UTF_8);
        byte[] signature;
        try {
            signature = DSA.createDigitalSignature(dataByte, dsa.getPrivateKey());
// dùng publickey của người dùng giải mã signature
            String publicKey = AccountDao.getPublicKeyByUser(userAccount);
            dsa.setPublicKeyFromText(publicKey);

// so sánh mã hash của hóa đơn do người dùng gửi lên với mã hash được lưu trữ trên database
            String dbhashing = VerifyDao.findHashCode(userAccount, orderId);
            byte[] dataByte2 = dbhashing.getBytes(StandardCharsets.UTF_8);
            String message = "";
            if (dsa.verifyDigitalSignature(dataByte2, signature, dsa.getPublicKey())) {
                message = "Đã xác thực hoá đơn thành công";
            } else {
                message = "Xác thực không thành công, hoá đơn hoặc key không hợp lệ!";
            }
            request.setAttribute("message", message);
            request.setAttribute("pageName", "Thông báo");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/notification.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            pw.println(-1);
            pw.flush();
            e.printStackTrace();
        }

    }

    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory filefactory = new DiskFileItemFactory();
        File filedir = (File) getServletContext().getAttribute("FILE_DIR_FILE");
        filefactory.setRepository(filedir);
        this.uploader = new ServletFileUpload(filefactory);
    }
}
