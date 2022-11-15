package Services;

import Beans.CartItem;
import Dao.CartDao;

import java.util.List;

public class CartService {
    public static boolean addToCart(int idCart, String idProduct, int quantity, String color, String size) {
        return CartDao.addToCart(idCart, idProduct, quantity, color, size);
    }
    public static List<CartItem> getCartItems(int idCart){
        return CartDao.getCartItems(idCart);
    }
}
