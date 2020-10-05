package app.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "pay_method_id")
    private int payMethodId;
    @Column(name = "delivery_method_id")
    private int deliveryMethodId;
    @Column(name = "order_status_id")
    private int orderStatusId;
    @Column(name = "pay_state_id")
    private int payStateId;
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "client_id")
    private int clientId;

    public Order() {
    }

    public Order(int payMethodId, int deliveryMethodId, int orderStatusId, int payStateId, int addressId, int clientId) {
        this.payMethodId = payMethodId;
        this.deliveryMethodId = deliveryMethodId;
        this.orderStatusId = orderStatusId;
        this.payStateId = payStateId;
        this.addressId = addressId;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", payMethodId=" + payMethodId +
                ", deliveryMethodId=" + deliveryMethodId +
                ", orderStatusId=" + orderStatusId +
                ", payStateId=" + payStateId +
                ", addressId=" + addressId +
                ", clientId=" + clientId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getPayMethodId() {
        return payMethodId;
    }

    public void setPayMethodId(int payMethodId) {
        this.payMethodId = payMethodId;
    }

    public int getDeliveryMethodId() {
        return deliveryMethodId;
    }

    public void setDeliveryMethodId(int deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getPayStateId() {
        return payStateId;
    }

    public void setPayStateId(int payStateId) {
        this.payStateId = payStateId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
