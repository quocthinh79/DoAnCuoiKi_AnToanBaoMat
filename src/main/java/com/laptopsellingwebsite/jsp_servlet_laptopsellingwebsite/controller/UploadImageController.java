package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@WebServlet(name = "UploadImageController", value = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "diyrrlmqk",
                "api_key", "137284888978213",
                "api_secret", "Rxu7XVXAxkeUXoEcwgt1s4dSpAs",
                "secure", true
        ));
        Part part = request.getPart("file");
        InputStream is = part.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[10240];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        PrintWriter out = response.getWriter();
        try {
            Map uploadResult = cloudinary.uploader().upload(buffer.toByteArray(), ObjectUtils.asMap("resource_type", "auto"));
            out.println(uploadResult.get("url"));
        } catch (Exception e) {
            out.println("Upload error");
        }
    }
}
