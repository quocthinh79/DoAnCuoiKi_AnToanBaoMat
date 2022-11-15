package Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    public static String getHash(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder result = new StringBuilder();
            for(byte b: digest) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
