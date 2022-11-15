package Services;

import Dao.AccountDao;
import Dao.RegisterDao;

import java.util.UUID;

public class RegisterService {
    public static int addRegister(String lastName, String firstName, String phoneNumber, String email, String
            userName, String password) {
        String token = "";
        boolean emailIsExist = email.equals(AccountDao.getEmail(email));
        boolean userNameIsExist = userName.equals(AccountDao.getUserName(userName));
        int result = 0;
        // neu email va username chua duoc dang ky thi se cho dang ky
        if (!emailIsExist && !userNameIsExist) {
            token = String.valueOf(UUID.randomUUID());
            password = HashService.getHash(password);
            boolean checkAddRegister = RegisterDao.addRegister(lastName, firstName, phoneNumber, email, userName, password, token);
            if (!checkAddRegister) {
                result = 1;
                // Da xay ra loi trong qua trinh dang ky vui long thu lai sau
                return result;
            }
            int registerID = RegisterDao.getRegisterID(userName, token);
            String link = "http://localhost:8080/WebBanQuanAo/confirm?id=" + registerID + "&token=" + token;
            SendMailService.sendMail(email, "Xac thuc dang ky", link);
        } else {
            if (emailIsExist) {
//                message = "Email da duoc dang ky vui long nhap email khac";
                result = 2;
                return result;
            } else {
//                message = "Ten tai khoan da ton tai nhap ten khac";
                result = 3;
                return result;
            }
        }
        return result;
    }
}
