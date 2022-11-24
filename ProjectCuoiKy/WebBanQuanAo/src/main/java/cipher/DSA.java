package cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class DSA {
    private static final String SIGNING_ALGORITHM = "SHA256withDSA";
    private static final String RSA = "DSA";
    public KeyPair keyPair;
    public PublicKey publicKey;
    public PrivateKey privateKey;
    public int keySize;
    public int maxSizeCanCipher;

    public DSA() {
        getKey();
    }

    public void getKey() {
        KeyPairGenerator keyGenerator = null;
        try {
            keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGenerator.initialize(2048, random);
            keyPair = keyGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void getKey(int keySize) {
        setKeySize(keySize);
        int maxSizeCanCipher = keySize / 8 - 11;
        setMaxSizeCanCipher(maxSizeCanCipher);
        KeyPairGenerator keyGenerator = null;
        try {
            keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
            keyGenerator.initialize(keySize);
            keyPair = keyGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public byte[] devideByteArray(Cipher cipher, byte[] input, String type)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        int i = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (type.equals("DECRYPT")) {
            maxSizeCanCipher += 11;
        }
        while (i < input.length) {
            i += maxSizeCanCipher;
            int temp = i;
            int back = temp - maxSizeCanCipher;
            if (temp >= input.length) {
                temp = input.length;
            }
            byte[] byteCut = Arrays.copyOfRange(input, back, temp);
            outputStream.write(cipher.doFinal(byteCut, 0, byteCut.length));
        }
        byte[] plaintTextResult = outputStream.toByteArray();
        return plaintTextResult;
    }

    public byte[] encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] plaintText = text.getBytes(StandardCharsets.UTF_8);
        return devideByteArray(cipher, plaintText, "ENCRYPT");
    }

    public byte[] encryptByteArr(byte[] text, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    public void encryptFile(String pathIn, String pathOut) {
        try {
            Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
            BufferedOutputStream cos = new BufferedOutputStream(new FileOutputStream(pathOut));
            byte[] buffer = new byte[1024];
            int read;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((read = bis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            byte[] byteArray = outputStream.toByteArray();
            cos.write(devideByteArray(cipher, byteArray, "ENCRYPT"));
            bis.close();
            cos.flush();
            cos.close();
            System.out.println("Encrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encryptFile(String pathIn, String pathOut, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(pathOut), cipher);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
            }
            bis.close();
            cos.flush();
            cos.close();
            System.out.println("Encrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String decrypt(byte[] text) throws Exception {
        Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(devideByteArray(cipher, text, "DECRYPT"), StandardCharsets.UTF_8);
    }

    public String decrypt(byte[] text, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plaintText = cipher.doFinal(text);
        return new String(plaintText, StandardCharsets.UTF_8);
    }

    public SecretKey decryptKey(byte[] text, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plaintText = cipher.doFinal(text);
        return new SecretKeySpec(plaintText, 0, plaintText.length, "DES");
    }

    public void decryptFile(String pathIn, String pathOut) {
        try {
            Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut));
            BufferedInputStream cis = new BufferedInputStream(new FileInputStream(pathIn));
            byte[] buffer = new byte[1024];
            int read;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((read = cis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            byte[] byteArray = outputStream.toByteArray();
            bos.write(devideByteArray(cipher, byteArray, "DECRYPT"));
            cis.close();
            bos.flush();
            bos.close();
            System.out.println("Decrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decryptFile(String pathIn, String pathOut, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("DSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut));
            CipherInputStream cis = new CipherInputStream(new FileInputStream(pathIn), cipher);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = cis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }
            cis.close();
            bos.flush();
            bos.close();
            System.out.println("Decrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaxSizeCanCipher() {
        return maxSizeCanCipher;
    }

    public void setMaxSizeCanCipher(int maxSizeCanCipher) {
        this.maxSizeCanCipher = maxSizeCanCipher;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String byteToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public byte[] stringToByte(String text) {
        return Base64.getDecoder().decode(text);
    }

    public void setPublicKeyFromText(String text) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(stringToByte(text));
        KeyFactory factory = KeyFactory.getInstance("DSA", "SUN");
        Key pblKey = factory.generatePublic(spec);
        setPublicKey((PublicKey) pblKey);
    }

    public void setPrivateKeyFromText(String text) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(stringToByte(text));
        KeyFactory factory = KeyFactory.getInstance("DSA", "SUN");
        Key priKey = factory.generatePrivate(spec);
        setPrivateKey((PrivateKey) priKey);
    }

    public static byte[] createDigitalSignature(byte[] input, PrivateKey Key) throws Exception {
        Signature dsa = Signature.getInstance(SIGNING_ALGORITHM);
        dsa.initSign(Key);
        dsa.update(input);
        return dsa.sign();
    }

    public static boolean verifyDigitalSignature(byte[] input, byte[] signatureToVerify, PublicKey key) throws Exception {
        Signature dsa = Signature.getInstance(SIGNING_ALGORITHM);
        dsa.initVerify(key);
        dsa.update(input);
        return dsa.verify(signatureToVerify);
    }

    public static void main(String[] args) throws Exception {
        String str = "ThanglonThinh";
        DSA DSA = new DSA();
        byte[] data = str.getBytes();

        ReadAndWriteFile.writeKeyToFile(DSA.byteToString(DSA.getPrivateKey().getEncoded()), "privateKey.bin");
        ReadAndWriteFile.writeKeyToFile(DSA.byteToString(DSA.getPrivateKey().getEncoded()), "publicKey.bin");

        DSA.setPrivateKeyFromText(ReadAndWriteFile.readKeyFromFile("src/main/java/cipher/privateKey.bin"));
        DSA.setPrivateKeyFromText(ReadAndWriteFile.readKeyFromFile("src/main/java/cipher/publicKey.bin"));

        // có chữ ký
        byte[] signature = DSA.createDigitalSignature(data, DSA.getPrivateKey());

//       xác thực chữ ký
        boolean verify = DSA.verifyDigitalSignature(data, signature, DSA.getPublicKey());
        System.out.println(verify);
    }
}
