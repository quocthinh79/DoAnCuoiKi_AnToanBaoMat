package signature_digital;

import com.aspose.pdf.DocMDPAccessPermissions;
import com.aspose.pdf.DocMDPSignature;
import com.aspose.pdf.Document;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfImage;
import com.spire.pdf.security.GraphicMode;
import com.spire.pdf.security.PdfCertificate;
import com.spire.pdf.security.PdfCertificationFlags;
import com.spire.pdf.security.PdfSignature;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author : Cong-Phuc, Nguyen
 * @since : 11/16/2022, Wed
 **/
public class DigitallySignPDF {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        test1();
//        test3();
//        ky();
//        kiemTra();
    }

    public static void test1() {
        //Create a PdfDocument object
        PdfDocument doc = new PdfDocument();

        //Load a sample PDF file
        doc.loadFromFile("D:/x.pdf");

        //Load a pfx certificate
        PdfCertificate cert = new PdfCertificate("D:/certificate.pfx", "1234");

        //Create a PdfSignature object
        PdfSignature signature = new PdfSignature(doc, doc.getPages().get(0), cert, "MySignature");
        Rectangle2D rect = new Rectangle2D.Float();

        rect.setFrame(new Point2D.Float((float) doc.getPages().get(0).getActualSize().getWidth() - 320, (float) doc.getPages().get(0).getActualSize().getHeight() - 140), new Dimension(270, 100));

//        signature.setBounds(rect);

        //Set the graphics mode
        signature.setGraphicMode(GraphicMode.Sign_Image_And_Sign_Detail);

        //Set the signature content
        signature.setNameLabel("Ho ten:");
        signature.setName("Nguyen Van A");
        signature.setContactInfoLabel("So dien thoai:");
        signature.setContactInfo("0123456789");
        signature.setDateLabel("Ngay ky:");
        signature.setDate(new java.util.Date());
        signature.setLocationInfoLabel("Dia chi:");
        signature.setLocationInfo("3/66/5 Ho Chi Minh");
        signature.setReasonLabel("Ly do ky: ");
        signature.setReason("Ky thu xem sao");
        signature.setDistinguishedNameLabel("Ten phan biet: ");
        signature.setDistinguishedName(signature.getCertificate().get_IssuerName().getName());
//        signature.setSignImageSource(PdfImage.fromFile("C:\\Users\\Administrator\\Desktop\\handwrittenSignature.png"));

        //Set the document permission
        signature.setDocumentPermissions(PdfCertificationFlags.Forbid_Changes);
        signature.setCertificated(true);

        //Save to another PDF file
        doc.saveToFile("D:/filePdfDaKy.pdf");
        doc.close();
        System.out.println("hoan thanh");
    }

    public static void test2() {
        Document doc = new Document("D:/testSignatureDigital.pdf");
        PdfFileSignature signature = new PdfFileSignature(doc);
        PKCS7 pkcs = new PKCS7("D:/certificate.pfx", "1234"); // Use PKCS7/PKCS7Detached objects
        DocMDPSignature docMdpSignature = new DocMDPSignature(pkcs, DocMDPAccessPermissions.FillingInForms);
        Rectangle rect = new Rectangle(100, 600, 400, 100);

//      Set signature appearance
//        signature.setSignatureAppearance("aspose-logo.png");

//      Create any of the three signature types
        signature.certify(1, "Lý do ký", "Contact", "Location", true, rect, docMdpSignature);


//      Save digitally signed PDF file
        signature.save("D:/filePdfDaKy.pdf");
        System.out.println("hoan thanh");
    }

    public static void test3() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024);

        KeyPair keyPair = kpg.generateKeyPair();

//      save privateKey
        PrivateKey privateKey = keyPair.getPrivate();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/privateKey.bin"));
        bos.write(privateKey.getEncoded());
        bos.close();

//      save publicKey
        PublicKey publicKey = keyPair.getPublic();
        bos = new BufferedOutputStream(new FileOutputStream("D:/publicKey.bin"));
        bos.write(publicKey.getEncoded());
        bos.close();
    }

    public static void ky() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
//      Up private key từ file
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/privateKey.bin"));
        byte[] data = new byte[bis.available()];
        bis.read(data);
        bis.close();

//      Tạo privateKey
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
        KeyFactory factory = KeyFactory.getInstance("DSA");
        PrivateKey privateKey = factory.generatePrivate(spec);

//       Ký
        Signature signature = Signature.getInstance("DSA");
        signature.initSign(privateKey, new SecureRandom());

//      Chọn file thực hiện ký số
        String fileName = "D:/testSignatureDigital.pdf";
        bis = new BufferedInputStream(new FileInputStream(fileName));
        byte[] data2 = new byte[bis.available()];

//      Chèn message vào đối tượng ký
        signature.update(data2);
        byte[] dataSignature = signature.sign();
        bis.close();

//      Lưu chữ ký số
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/dayLaFileChuKy"));
        bos.write(dataSignature);
        bos.close();
    }

    public static void kiemTra() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/publicKey.bin"));
        byte[] data = new byte[bis.available()];
        bis.read(data);
        bis.close();

//      Tao public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory factory = KeyFactory.getInstance("DSA");
        PublicKey publicKey = factory.generatePublic(spec);

//      Tao signature
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(publicKey);

//      Chon file kiem tra
        String fileName = "D:/testSignatureDigital.pdf";
        bis = new BufferedInputStream(new FileInputStream(fileName));
        byte[] byteFile = new byte[bis.available()];
        bis.close();

//      Them message vao signature
        signature.update(byteFile);

//      Kiem chung chu ky tren Message
//      Nap chu ky signature tu file
        bis = new BufferedInputStream(new FileInputStream("D:/dayLaFileChuKy"));
        byte[] byteSign = new byte[bis.available()];
        bis.read(byteSign);
        bis.close();

//      Ket qua kiem chung
        boolean result = signature.verify(byteSign);
        if (result) {
            System.out.println("Message is verified");
        } else {
            System.out.println("Message is not verified");
        }
    }
}
