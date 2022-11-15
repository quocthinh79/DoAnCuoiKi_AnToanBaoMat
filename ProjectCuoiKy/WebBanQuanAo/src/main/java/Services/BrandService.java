package Services;

import Beans.Brand;
import Dao.BrandDao;

import java.util.List;

/**
 * @author : Cong-Phuc, Nguyen
 * @lastest_update : 8/11/2022
 **/
public class BrandService {
    public static List<Brand> getBrandList() {
        return BrandDao.getBrandList();
    }

    public static boolean addBrand(String brand_id, String brand_name) {
        return BrandDao.addBrand(brand_id, brand_name);
    }

    public static boolean deleteBrand(String brand_id) {
        return BrandDao.deleteBrand(brand_id);
    }

    public static boolean updateBrand(String brand_id, String brand_name) {
        return BrandDao.updateBrand(brand_id, brand_name);
    }
}
