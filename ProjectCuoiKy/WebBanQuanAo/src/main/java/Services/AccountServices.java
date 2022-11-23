package Services;

import Beans.Account;
import Dao.AccountDao;

import java.util.ArrayList;
import java.util.List;

public class AccountServices {
    private static List<Account> accounts = new ArrayList<Account>();

    public static Account getAccount(String userName, String passWord, boolean isHashCode) {
        String hashPassword = "";
        if (isHashCode) {
            hashPassword = HashService.getHash(passWord);
        } else {
            hashPassword = passWord;
        }
        Account account = AccountDao.getUser(userName, hashPassword);
        return account;
    }


    public static String vadiladate(String userName, String passWord) {
        String result = "";
        // kiểm tra ít nhất 4 kí tự có 1 chữ cái và 1 chữ số
        String regexCheckPassWord = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
        if (userName.equals("")) {
            result = "Tài khoản không được để trống!";
        } else {
            if (!passWord.matches(regexCheckPassWord)) {
                result = "Mật khẩu phải có ít nhất 4 kí tự, một chữ cái và một chữ số!";
            }
        }
        return result;
    }

    public static String isExistUsername(String username) {
        return AccountDao.getUserName(username) == null ? "" : "Tài khoản đã tồn tại!";
    }

    public static int addNewAccount(String lastName, String firstName, String phoneNumber, String email, String
            userName, String password) {
        boolean emailIsExist = email.equals(AccountDao.getEmail(email));
        int result = 0;
        if (!emailIsExist) {
            boolean userNameIsExist = userName.equals(AccountDao.getUserName(userName));
            if (!userNameIsExist) {
                password = HashService.getHash(password);
                boolean addAcc = AccountDao.addAccount(lastName, firstName, phoneNumber, email, userName, password);
                if(!addAcc) {
                    result = 1;
                }
            } else {
                result = 3;
            }
        } else {
            result = 2;
        }
        return result;
    }

    public static boolean updateAccount(String idAccount, String lastname, String firstname, String phone, String email, String address, String username, String role){
        return AccountDao.updateAccount(idAccount, lastname, firstname, phone, email, address, username, role);
    }
}
