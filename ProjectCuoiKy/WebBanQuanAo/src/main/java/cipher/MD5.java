package cipher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : Cong-Phuc, Nguyen
 * @since : 11/26/2022, Sat
 **/
public class MD5 {
    public static String MD5 = "MD5";

    public static void main(String[] args) {
        String text = "Đại học nông lâm";
        String hashedText = MD5Text(text);
        System.out.println(hashedText);
    }

    // convert byte sang hex
    public static String byteToHex(byte[] data) {
        BigInteger number = new BigInteger(1, data);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    // băm text
    public static String MD5Text(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] messageDigest = md.digest(text.getBytes());
            return byteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // băm file
    public static String MD5File(File file) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(MD5);
            if (!file.exists()) {
                return "";
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] dataBytes = new byte[102400];
            int nread = 0;
            while ((nread = bis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] byteData = md.digest();
            bis.close();
            return byteToHex(byteData);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
