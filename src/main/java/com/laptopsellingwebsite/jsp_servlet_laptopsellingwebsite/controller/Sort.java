package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.controller;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "Sort", value = "/Sort")
public class Sort extends HttpServlet {
    private static final Multimap<String, String> map = TreeMultimap.create();
    private static final int count = 0;

    private static void sortFuction(Multimap<String, String> map, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
//        map = new HashMap<>();
        HttpSession session = request.getSession();
        String hangsx = (String) session.getAttribute("idHang");
        List<Product> list = null;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        currencyVN.setCurrency(Currency.getInstance("VND"));

//        String orderBy = request.getParameter("value");
//        session.setAttribute("orderBy", orderBy);

        String orderBy = (String) session.getAttribute("orderBy");
        request.setAttribute("orderBy", orderBy);
        int page = 1;
        if (request.getParameter("page") != null && request.getParameter("page") != "") {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
        int limit = 24;
        int totalPage;
        int total = 0;
        if (map.isEmpty()) {
            if (hangsx != null && hangsx != "") {
                totalPage = ProductService.getInstance().getTotalPageByProducer(hangsx);
                total = (int) Math.ceil((double) totalPage / (double) limit);
                request.setAttribute("totalPage", total);
                list = ProductService.getInstance().getProductManufacturer(hangsx, orderBy, limit, page);
                request.setAttribute("allProducer", ProductService.getInstance().getProducerWithID(hangsx));
                request.setAttribute("filter", ProductService.getInstance().getProductManufacturer(hangsx));
            } else {
                totalPage = ProductService.getInstance().getTotalPage();
                total = (int) Math.ceil((double) totalPage / (double) limit);
                request.setAttribute("totalPage", total);
                list = ProductService.getInstance().getAllProduct(orderBy,limit, page);
                request.setAttribute("allProducer", ProductService.getInstance().getAllProducer());
                request.setAttribute("filter", ProductService.getInstance().getAllProduct());
            }
        } else if (hangsx == null) {
            totalPage = ProductService.getInstance().sortProductTotalPage(map);
            total = (int) Math.ceil((double) totalPage / (double) limit);
            request.setAttribute("totalPage", total);
            request.setAttribute("allProducer", ProductService.getInstance().getAllProducer());
            request.setAttribute("filter", ProductService.getInstance().getAllProduct());
            list = ProductService.getInstance().sortProduct(map, orderBy, limit, page);
        } else {
            totalPage = ProductService.getInstance().sortProductTotalPageByProducer(map, hangsx);
            total = (int) Math.ceil((double) totalPage / (double) limit);
            request.setAttribute("totalPage", total);
            request.setAttribute("allProducer", ProductService.getInstance().getProducerWithID(hangsx));
            request.setAttribute("filter", ProductService.getInstance().getProductManufacturer(hangsx));
            list = ProductService.getInstance().sortProductWithProducer(map, hangsx, orderBy, limit, page);

        }
        session.setAttribute("map", map);
        request.setAttribute("valuesMap", map.values());
        request.setAttribute("allProduct", list);

        request.setAttribute("lowestPrice", session.getAttribute("lowestPrice"));
        request.setAttribute("highPrice", session.getAttribute("highPrice"));

        if (session.getAttribute("lowestPrice") != null && session.getAttribute("highPrice") != null){
            request.setAttribute("display", "block");
        }

        request.getRequestDispatcher("jsp/all-product.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String value = request.getParameter("value");
        String name = request.getParameter("name");
        HttpSession session = request.getSession();
        switch (name) {
            case "hang":
                if (map.values().contains(value)) {
                    map.remove("hang", value);
                } else {
                    map.put("hang", value);
                }
                break;
            case "mau":
                if (map.values().contains(value)) {
                    map.remove("mau", value);
                } else {
                    map.put("mau", value);
                }
                break;
            case "cpu":
                if (map.values().contains(value)) {
                    map.remove("SUBSTRING_INDEX(SUBSTRING_INDEX(cpu, ' ', 3), ' ', -2)", value);
                } else {
                    map.put("SUBSTRING_INDEX(SUBSTRING_INDEX(cpu, ' ', 3), ' ', -2)", value);
                }
                break;
            case "ram":
                if (map.values().contains(value)) {
                    map.remove("ram", value);
                } else {
                    map.put("ram", value);
                }
                break;
            case "series":
                if (map.values().contains(value)) {
                    map.remove("series", value);
                } else {
                    map.put("series", value);
                }
                break;
            case "btn-filter-price":
                map.get("GIABAN").clear();
                String strLow = request.getParameter("lowestPrice");
                String strHigh = request.getParameter("highPrice");
                session.setAttribute("lowestPrice", strLow);
                session.setAttribute("highPrice", strHigh);
                if (!strLow.equals("") && !strHigh.equals("")) {
                    map.put("GIABAN", strLow.replaceAll(",", ""));
                    map.put("GIABAN", strHigh.replaceAll(",", ""));
                }
                break;
        }
        map.entries().removeIf(e -> e.getValue() == null);
        sortFuction(map, response, request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}