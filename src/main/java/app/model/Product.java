package app.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer price;
    @Column
    private Integer size;
    @Column
    private String brand;
    @Column
    private String color;
    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Transient
    private Integer categoryId;

    public Product() {
    }

    public Product(String name, Integer price, Integer size,
                   String brand, String color, Category category, Integer quantityInStock) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.brand = brand;
        this.color = color;
        this.category = category;
        this.quantityInStock = quantityInStock;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", category=" + category +
                ", categoryId=" + categoryId +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
