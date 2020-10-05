package app.model;

import javax.persistence.*;

@Entity
@Table(name = "payment_states")
public class PaymentState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;

    public PaymentState(String name) {
        this.name = name;
    }

    public PaymentState() {
    }

    @Override
    public String toString() {
        return "PaymentState{" +
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
