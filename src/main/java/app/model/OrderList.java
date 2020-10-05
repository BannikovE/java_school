package app.model;

import javax.persistence.*;

@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "product_id")
    private int productId;

    public OrderList() {
    }

    public OrderList(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
