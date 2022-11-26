package writetopdf;

import Beans.CartItem;
import Beans.OrderInfor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriteDataToPdf {
    public void writeObjectToPdf(OrderInfor orderInfor) throws IOException {
        Document document = new Document();
        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream("src/main/java/writetopdf/CompletePDF/CompletePDF.pdf"));
            document.open();

            Paragraph id = new Paragraph("- ID Order: " + orderInfor.getId());
            id.setSpacingBefore(10);
            id.setAlignment(Element.ALIGN_LEFT);
            document.add(id);

            Paragraph firstName = new Paragraph("- First name: " + orderInfor.getFirstName());
            firstName.setSpacingBefore(10);
            firstName.setAlignment(Element.ALIGN_LEFT);
            document.add(firstName);

            Paragraph lastName = new Paragraph("- Last name: " + orderInfor.getLastName());
            lastName.setSpacingBefore(10);
            lastName.setAlignment(Element.ALIGN_LEFT);
            document.add(lastName);

            Paragraph address = new Paragraph("- Address: " + orderInfor.getAddress());
            address.setSpacingBefore(10);
            address.setAlignment(Element.ALIGN_LEFT);
            document.add(address);

            Paragraph phone = new Paragraph("- Phone: " + orderInfor.getPhone());
            phone.setSpacingBefore(10);
            phone.setAlignment(Element.ALIGN_LEFT);
            document.add(phone);

            Paragraph totalPrice = new Paragraph("- Total price: " + orderInfor.getTotalPrice());
            totalPrice.setSpacingBefore(10);
            totalPrice.setAlignment(Element.ALIGN_LEFT);
            document.add(totalPrice);

            List<CartItem> listCartItem = orderInfor.getListItem();
            listCartItem.forEach((item) -> {
                try {
                    Paragraph itemCartTitle = new Paragraph("- Item cart: ");
                    itemCartTitle.setSpacingBefore(10);
                    itemCartTitle.setAlignment(Element.ALIGN_LEFT);
                    document.add(itemCartTitle);

                    Paragraph priceItem = new Paragraph("Price item: " + item.getPrice());
                    priceItem.setSpacingBefore(10);
                    priceItem.setIndentationLeft(40f);
                    priceItem.setAlignment(Element.ALIGN_LEFT);
                    document.add(priceItem);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        CartItem cartItem = new CartItem();
        cartItem.setColor("String color");
        cartItem.setIdProduct("String id");
        cartItem.setPrice(3.4);
        cartItem.setQuantity(3);
        cartItem.setNameProduct("String name");
        cartItem.setThumbnail("String thum");
        CartItem cartItem2 = new CartItem();
        cartItem2.setColor("String color 2");
        cartItem2.setIdProduct("String id 2");
        cartItem2.setPrice(3.421);
        cartItem2.setQuantity(3332);
        cartItem2.setNameProduct("String name 2");
        cartItem2.setThumbnail("String thum 2");
        CartItem cartItem3 = new CartItem();
        cartItem3.setColor("String color 3");
        cartItem3.setIdProduct("String id 3");
        cartItem3.setPrice(3.4444);
        cartItem3.setQuantity(321);
        cartItem3.setNameProduct("String name 3");
        cartItem3.setThumbnail("String thum 3");
        List<CartItem> listCartItem = new ArrayList<>();
        listCartItem.add(cartItem);
        listCartItem.add(cartItem2);
        listCartItem.add(cartItem3);
        OrderInfor orderInfor = new OrderInfor("IDOrder", listCartItem, 32.2, "String name", "String name", "String phone", "String address");

        WriteDataToPdf t = new WriteDataToPdf();
        t.writeObjectToPdf(orderInfor);
    }

}
