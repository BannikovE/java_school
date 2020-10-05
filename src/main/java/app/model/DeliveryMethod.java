package app.model;

import javax.persistence.*;

@Entity
@Table(name = "delivery_methods")
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;

    public DeliveryMethod() {
    }

    public DeliveryMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeliveryMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
