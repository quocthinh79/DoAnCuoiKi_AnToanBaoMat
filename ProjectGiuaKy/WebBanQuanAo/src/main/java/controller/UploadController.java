package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@WebServlet(name = "upload", value = "/upload")
@MultipartConfig()
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "Upload");
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/upload.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Part img = request.getPart("img");
        String readPath = request.getServletContext().getRealPath("/assets/uploads");
        String fileName = Paths.get(img.getSubmittedFileName()).getFileName().toString();

        File folder = new File(readPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        InputStream inputStream = img.getInputStream();
        FileOutputStream fis = new FileOutputStream(readPath + "/" + fileName);
        byte[] data = new byte[1024];
        int byteRead = 0;
        while ((byteRead = inputStream.read(data, 0, data.length)) != -1) {
            fis.write(data, 0, byteRead);
        }
        fis.close();
        inputStream.close();
//        img.write(readPath+"/"+fileName);
        doGet(request, response);
    }
}
