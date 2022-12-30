package Services;

import Dao.AccountDao;

public class GenerateKeyServices {
    public static boolean updatePublicKey(String idAccount,  String publicKey){
        return AccountDao.addPublicKey(idAccount, publicKey);
    }
}
