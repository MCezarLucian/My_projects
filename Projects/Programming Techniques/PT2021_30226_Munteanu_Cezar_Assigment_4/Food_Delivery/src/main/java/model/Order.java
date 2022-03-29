package model;

import java.time.LocalDateTime;

public class Order {

    int orderID;
    int clientID;
    LocalDateTime orderDate;
    int total;

    public Order(int orderID, int clientID, LocalDateTime orderDate, int total) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate +
                ", total=" + total +
                '}';
    }
}
