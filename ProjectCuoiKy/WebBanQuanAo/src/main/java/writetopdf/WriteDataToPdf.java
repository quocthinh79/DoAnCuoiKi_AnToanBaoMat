package writetopdf;

import Beans.CartItem;
import Beans.OrderInfor;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriteDataToPdf {
    public static WriteDataToPdf instance;

    private WriteDataToPdf() {
    }

    public static WriteDataToPdf getInstance() {
        if (instance == null) {
            instance = new WriteDataToPdf();
        }
        return instance;
    }

    public void writeObjectToPdf(OrderInfor orderInfor, String hostName) throws IOException {
        Document document = new Document();
        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream(hostName + "/CompletePDF.pdf"));
            document.open();

            BaseFont bf = BaseFont.createFont("ArialUnicodeMS.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);

            Paragraph id = new Paragraph("- ID Đơn hàng: " + orderInfor.getId(), font);
            id.setSpacingBefore(10);
            id.setAlignment(Element.ALIGN_LEFT);
            document.add(id);

            Paragraph firstName = new Paragraph("- Họ tên khách hàng: " + orderInfor.getFirstName(), font);
            firstName.setSpacingBefore(10);
            firstName.setAlignment(Element.ALIGN_LEFT);
            document.add(firstName);

            Paragraph address = new Paragraph("- Địa chỉ: " + orderInfor.getAddress(), font);
            address.setSpacingBefore(10);
            address.setAlignment(Element.ALIGN_LEFT);
            document.add(address);

            Paragraph phone = new Paragraph("- Số điện thoại: " + orderInfor.getPhone(), font);
            phone.setSpacingBefore(10);
            phone.setAlignment(Element.ALIGN_LEFT);
            document.add(phone);

            List<CartItem> listCartItem = orderInfor.getListItem();
            listCartItem.forEach((item) -> {
                try {
                    Paragraph itemCartTitle = new Paragraph("- Sản phẩm: ", font);
                    itemCartTitle.setSpacingBefore(10);
                    itemCartTitle.setAlignment(Element.ALIGN_LEFT);
                    document.add(itemCartTitle);

                    Paragraph idItem = new Paragraph("ID sản phẩm: " + item.getIdProduct(), font);
                    idItem.setSpacingBefore(10);
                    idItem.setIndentationLeft(40f);
                    idItem.setAlignment(Element.ALIGN_LEFT);
                    document.add(idItem);

                    Paragraph productName = new Paragraph("Tên sản phẩm: " + item.getNameProduct(), font);
                    productName.setSpacingBefore(10);
                    productName.setIndentationLeft(40f);
                    productName.setAlignment(Element.ALIGN_LEFT);
                    document.add(productName);

                    Paragraph quantity = new Paragraph("Số lượng: " + item.getQuantity(), font);
                    quantity.setSpacingBefore(10);
                    quantity.setIndentationLeft(40f);
                    quantity.setAlignment(Element.ALIGN_LEFT);
                    document.add(quantity);

                    Paragraph priceItem = new Paragraph("Giá tiền mỗi sản phẩm: " + String.format("%,.2f", (item.getPrice())) , font);
                    priceItem.setSpacingBefore(10);
                    priceItem.setIndentationLeft(40f);
                    priceItem.setAlignment(Element.ALIGN_LEFT);
                    document.add(priceItem);

                    Paragraph totalPriceItem = new Paragraph("Tổng tiền sản phẩm: " + String.format("%,.2f", (item.getPrice() * item.getQuantity())) , font);
                    totalPriceItem.setSpacingBefore(10);
                    totalPriceItem.setIndentationLeft(40f);
                    totalPriceItem.setAlignment(Element.ALIGN_LEFT);
                    document.add(totalPriceItem);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            Paragraph totalPrice = new Paragraph("- Tổng giá trị đơn hàng: " + String.format("%,.2f", orderInfor.getTotalPrice()) , new Font(bf, 22, 1, BaseColor.RED));
            totalPrice.setSpacingBefore(10);
            totalPrice.setAlignment(Element.ALIGN_LEFT);
            document.add(totalPrice);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
