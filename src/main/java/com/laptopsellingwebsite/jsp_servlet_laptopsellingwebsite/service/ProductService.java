package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.service;

import com.google.common.collect.Multimap;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.Product;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.beans.ProductWithStatus;
import com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.dao.ProductDao;

import java.util.List;
import java.util.Map;

public class ProductService {
    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public List<Product> getAllProduct() {
        return ProductDao.getInstance().getAllProduct();
    }

    public List<ProductWithStatus> getAllProductWithStatus() {
        return ProductDao.getInstance().getAllProductWithStatus();
    }

    public List<Product> getAllProduct(int limit, int page) {
        return ProductDao.getInstance().getAllProduct(limit, page);
    }

    public List<ProductWithStatus> getAllProductWithStatus(int limit, int page) {
        return ProductDao.getInstance().getAllProductWithStatus(limit, page);
    }

    public int getTotalPage() {
        return ProductDao.getInstance().getTotalPage();
    }

    public List getAllProducer() {
        return ProductDao.getInstance().getAllProducer();
    }

    public List getAllProduct(String temp, int limit, int page) {
        return ProductDao.getInstance().getAllProduct(temp, limit, page);
    }

    public List getTopProduct1(int num) {
        return ProductDao.getInstance().getTopProduct1(num);
    }

    public List getTopProductBestSeller(int num) {
        return ProductDao.getInstance().getTopProductBestSeller(num);
    }

    public List getProductManufacturer(String manufacturer) {
        return ProductDao.getInstance().getProductManufacturer(manufacturer);
    }

    public List getProductManufacturer(String manufacturer, String temp, int limit, int page) {
        return ProductDao.getInstance().getProductManufacturer(manufacturer, temp, limit, page);
    }

    public List getProducerWithID(String producer) {
        return ProductDao.getInstance().getProducerWithID(producer);
    }

    public List getProductWithID(String ID) {
        return ProductDao.getInstance().getProductWithID(ID);
    }

    public List getProductWithProducer(String producer) {
        return ProductDao.getInstance().getProductWithProducer(producer);
    }

    public List getTopProducer(int num) {
        return ProductDao.getInstance().getTopProducer(num);
    }

    public List sortProduct(Multimap<String, String> map, String temp, int limit, int page) {
        return ProductDao.getInstance().sortProduct(map, temp, limit, page);
    }

    public int sortProductTotalPage(Multimap<String, String> map) {
        return ProductDao.getInstance().sortProductTotalPage(map);
    }

    public int sortProductTotalPageByProducer(Multimap<String, String> map, String hangsx) {
        return ProductDao.getInstance().sortProductTotalPageByProducer(map, hangsx);
    }

    public List sortProductWithProducer(Multimap<String, String> map, String hangsx, String temp, int limit, int page) {
        return ProductDao.getInstance().sortProductWithProducer(map, hangsx, temp, limit, page);
    }

    public int getTotalPageByProducer(String producer) {
        return ProductDao.getInstance().getTotalPageByProducer(producer);
    }

    public List getProductManufacturer(String manufacturer, int limit, int page) {
        return ProductDao.getInstance().getProductManufacturer(manufacturer, limit, page);
    }

    public List searchClick(String txt) {
        return ProductDao.getInstance().searchClick(txt);
    }

    public int getTotalPageSearch(String txt) {
        return ProductDao.getInstance().getTotalPageSearch(txt);
    }

    public List search(String txt) {
        return ProductDao.getInstance().search(txt);
    }

    public List sortWithPrice(int highPrice, int lowPrice) {
        return ProductDao.getInstance().sortWithPrice(highPrice, lowPrice);
    }

    public List sortPriceWithProducer(int highPrice, int lowPrice, String hangSX) {
        return ProductDao.getInstance().sortPriceWithProducer(highPrice, lowPrice, hangSX);
    }

    public List sortProduct(Multimap<String, String> map, String temp) {
        return ProductDao.getInstance().sortProduct(map, temp);
    }

    public List sortProductWithProducer(Map<String, String> map, String hangsx) {
        return ProductDao.getInstance().sortProductWithProducer(map, hangsx);
    }

    public List sortProductWithProducer(Multimap<String, String> map, String hangsx, String temp) {
        return ProductDao.getInstance().sortProductWithProducer(map, hangsx, temp);
    }

}
