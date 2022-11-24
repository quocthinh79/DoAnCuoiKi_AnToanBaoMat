package Beans;

import java.util.List;

public class OrderInfor {
    public String id;
    public List<CartItem> listItem;
    public double totalPrice;
    public String firstName;
    public String lastName;
    public String phone;
    public String address;

    public OrderInfor(String id, List<CartItem> listItem, double totalPrice, String firstName, String lastName, String phone, String address) {
        this.id = id;
        this.listItem = listItem;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
