package Beans;

import java.util.List;

public class OrderInfor {
    private int id;
    private List<CartItem> listItem;
    private double totalPrice;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public OrderInfor(int id, List<CartItem> listItem, double totalPrice, String Name, String phone, String address) {
        this.id = id;
        this.listItem = listItem;
        this.totalPrice = totalPrice;
        this.firstName = Name;
        this.phone = phone;
        this.address = address;
    }

    public OrderInfor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getListItem() {
        return listItem;
    }

    public void setListItem(List<CartItem> listItem) {
        this.listItem = listItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String listString = "[";
        for (CartItem c : listItem){
            listString += c.toString()+"\n";
        }
        listString += "]";
        return "OrderInfor{" +
                ", totalPrice=" + totalPrice +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                "listItem=" + listString +
                '}';
    }
}
