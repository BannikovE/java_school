package app.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
//    public Product() {
//    }
    @Id
    @Column (name = "product_id")
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private int price;
//    @Column (name = "category_id")
//    private int category;
    @Column
    private int size;
    @Column
    private String brand;
    @Column
    private String color;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Transient
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", weight=" + size +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public Product(int id, String name, int price, Category category, int size, String brand, String color) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.size = size;
//        this.brand = brand;
//        this.color = color;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
