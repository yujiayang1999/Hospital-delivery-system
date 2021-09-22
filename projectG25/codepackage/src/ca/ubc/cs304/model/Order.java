package ca.ubc.cs304.model;

public class Order {
    int order_num;
    int order_date;

    public Order(int order_num, int order_date) {
        this.order_num = order_num;
        this.order_date = order_date;
    }

    public int getOrder_num() {
        return order_num;
    }

    public int getOrder_date() {
        return order_date;
    }
}
