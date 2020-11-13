package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private int index;
    @Column
    private String street;
    @Column
    private int building;
    @Column
    private int room;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Transient
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "  country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", index=" + index +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", room=" + room +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Address() {
    }

    public Address(String country, String city, int index,
                   String street, int building, int room, User user) {
        this.country = country;
        this.city = city;
        this.index = index;
        this.street = street;
        this.building = building;
        this.room = room;
        this.user = user;
    }
}
